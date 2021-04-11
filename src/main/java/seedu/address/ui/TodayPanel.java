package seedu.address.ui;

import static seedu.address.commons.core.Messages.MESSAGE_NO_DEADLINES_TO_DISPLAY_TODAY;
import static seedu.address.commons.core.Messages.MESSAGE_NO_EVENTS_TO_DISPLAY_TODAY;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.project.Project;
import seedu.address.model.task.deadline.DeadlineWithProject;
import seedu.address.model.task.repeatable.EventWithProject;

/**
 * Panel displaying today screen.
 */
public class TodayPanel extends UiPart<Region> {
    private static final String FXML = "TodayPanel.fxml";

    private final LocalDate currentDate;
    private final ObservableList<Project> projectsList;

    private final ListView<EventWithProject> eventsListView = new ListView<>();
    private final ListView<DeadlineWithProject> deadlinesListView = new ListView<>();

    @FXML
    private Label date;
    @FXML
    private StackPane eventsListViewPlaceholder;
    @FXML
    private StackPane deadlinesListViewPlaceholder;

    /**
     * Creates a {@code TodayPanel}.
     *
     * @param colabFolder CoLAB folder used to create today panel.
     */
    public TodayPanel(ReadOnlyColabFolder colabFolder, LocalDate date) {
        super(FXML);

        this.projectsList = colabFolder.getProjectsList();
        this.currentDate = date;
        this.date.setText(DateUtil.decodeDateWithDay(currentDate));

        initEventsSection();
        initDeadlinesSection();
    }

    private void initDeadlinesSection() {
        deadlinesListView.setCellFactory(listView -> new TodayDeadlineListViewCell());
        ObservableList<DeadlineWithProject> observableList = FXCollections.observableArrayList();

        for (Project project : projectsList) {
            project.getDeadlinesOnDate(currentDate)
                    .forEach(x -> observableList.add(new DeadlineWithProject(x, project.getProjectName())));
        }

        SortedList<DeadlineWithProject> sortedDeadlineList = new SortedList<>(observableList,
                Comparator.comparing(DeadlineWithProject::getDescription));

        deadlinesListView.setItems(sortedDeadlineList);


        if (observableList.isEmpty()) {
            Label noDeadlinesPlaceholder = new Label();
            noDeadlinesPlaceholder.setText(MESSAGE_NO_DEADLINES_TO_DISPLAY_TODAY);
            deadlinesListViewPlaceholder.getChildren().add(noDeadlinesPlaceholder);
        } else {
            deadlinesListViewPlaceholder.getChildren().add(deadlinesListView);
        }
    }

    private void initEventsSection() {
        eventsListView.setCellFactory(listView -> new TodayEventListViewCell());
        ObservableList<EventWithProject> observableList = FXCollections.observableArrayList();

        for (Project project : projectsList) {
            project.getEventsOnDate(currentDate)
                    .forEach(x -> observableList.add(new EventWithProject(x, project.getProjectName())));
        }

        SortedList<EventWithProject> sortedEventList = new SortedList<>(observableList,
                Comparator.comparing(EventWithProject::getTime).thenComparing(EventWithProject::getDescription));

        eventsListView.setItems(sortedEventList);

        if (observableList.isEmpty()) {
            Label noEventsPlaceholder = new Label();
            noEventsPlaceholder.setText(MESSAGE_NO_EVENTS_TO_DISPLAY_TODAY);
            eventsListViewPlaceholder.getChildren().add(noEventsPlaceholder);
        } else {
            eventsListViewPlaceholder.getChildren().add(eventsListView);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Event} using an {@code EventCard}.
     */
    static class TodayEventListViewCell extends ListCell<EventWithProject> {
        @Override
        protected void updateItem(EventWithProject eventWithProject, boolean empty) {
            super.updateItem(eventWithProject, empty);

            if (empty || eventWithProject == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TodayEventCard(eventWithProject).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code DeadlineWithProject} using
     * a {@code TodayDeadlineCard}.
     */
    static class TodayDeadlineListViewCell extends ListCell<DeadlineWithProject> {
        @Override
        protected void updateItem(DeadlineWithProject deadlineWithProject, boolean empty) {
            super.updateItem(deadlineWithProject, empty);

            if (empty || deadlineWithProject == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TodayDeadlineCard(deadlineWithProject).getRoot());
            }
        }
    }
}
