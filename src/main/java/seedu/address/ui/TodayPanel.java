package seedu.address.ui;

import static seedu.address.commons.core.Messages.MESSAGE_NO_DEADLINES_TO_DISPLAY;
import static seedu.address.commons.core.Messages.MESSAGE_NO_EVENTS_TO_DISPLAY;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.repeatable.Event;

/**
 * Panel displaying today screen.
 */
public class TodayPanel extends UiPart<Region> {
    private static final String FXML = "TodayPanel.fxml";

    private final LocalDate currentDate;
    private final ObservableList<Project> projectsList;

    private final ListView<Event> eventsListView = new ListView<>();
    private final ListView<CompletableDeadline> deadlinesListView = new ListView<>();

    private Label noEventsPlaceholder;
    private Label noDeadlinesPlaceholder;

    @FXML
    private Label date;

    @FXML
    private StackPane eventsListViewPlaceholder;

    @FXML
    private StackPane deadlinesListViewPlaceholder;

    /**
     * Creates a {@code TodayPanel}.
     * @param colabFolder CoLAB folder used to create today panel.
     */
    public TodayPanel(ReadOnlyColabFolder colabFolder, LocalDate date) {
        super(FXML);

        this.projectsList = colabFolder.getProjectsList();
        this.currentDate = date;
        this.date.setText(DateUtil.decodeDate(currentDate));

        initEventsSection();
        initDeadlinesSection();
    }

    private void initDeadlinesSection() {
        ObservableList<CompletableDeadline> deadlinesListViewItems = deadlinesListView.getItems();
        deadlinesListView.setCellFactory(listView -> new CompletableDeadlineListViewCell());
        for (Project project : projectsList) {
            FilteredList<CompletableDeadline> deadlineList = project.getDeadlinesOnDate(currentDate);
            deadlinesListViewItems.addAll(deadlineList);
        }

        if (deadlinesListViewItems.isEmpty()) {
            noDeadlinesPlaceholder = new Label();
            noDeadlinesPlaceholder.setText(MESSAGE_NO_DEADLINES_TO_DISPLAY);
            deadlinesListViewPlaceholder.getChildren().add(noDeadlinesPlaceholder);
        } else {
            deadlinesListViewPlaceholder.getChildren().add(deadlinesListView);
        }
    }

    private void initEventsSection() {
        ObservableList<Event> eventsListViewItems = eventsListView.getItems();
        eventsListView.setCellFactory(listView -> new EventListViewCell());
        for (Project project : projectsList) {
            FilteredList<Event> eventList = project.getEventsOnDate(currentDate);
            eventsListViewItems.addAll(eventList);
        }

        if (eventsListViewItems.isEmpty()) {
            noEventsPlaceholder = new Label();
            noEventsPlaceholder.setText(MESSAGE_NO_EVENTS_TO_DISPLAY);
            eventsListViewPlaceholder.getChildren().add(noEventsPlaceholder);
        } else {
            eventsListViewPlaceholder.getChildren().add(eventsListView);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Event} using an {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CompletableDeadline} using
     * a {@code CompletableDeadlineCard}.
     */
    class CompletableDeadlineListViewCell extends ListCell<CompletableDeadline> {
        @Override
        protected void updateItem(CompletableDeadline completableDeadline, boolean empty) {
            super.updateItem(completableDeadline, empty);

            if (empty || completableDeadline == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompletableDeadlineCard(completableDeadline).getRoot());
            }
        }
    }
}
