package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.ReadOnlyProjectsFolder;
import seedu.address.model.project.Project;
import seedu.address.model.task.repeatable.Event;

/**
 * Panel displaying today screen.
 */
public class TodayPanel extends UiPart<Region> {
    private static final String FXML = "TodayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodayPanel.class);
    private LocalDate currentDate;
    private ObservableList<Project> projectsList;

    @FXML
    private Label date;

    @FXML
    private ListView<Event> eventsListView;

    /**
     * Creates a {@code TodayPanel}.
     * @param projectsFolder Projects folder used to create today panel.
     */
    public TodayPanel(ReadOnlyProjectsFolder projectsFolder) {
        super(FXML);

        this.projectsList = projectsFolder.getProjectsList();
        this.currentDate = LocalDate.now();
        date.setText(DateUtil.decodeDate(currentDate));

        initEventsSection();
        initDeadlinesSection();
    }

    private void initDeadlinesSection() {

    }

    private void initEventsSection() {
        ObservableList<Event> eventsListViewItems = eventsListView.getItems();
        eventsListView.setCellFactory(listView -> new EventListViewCell());
        for (Project project : projectsList) {
            FilteredList<Event> eventList = project.getEventsOnDate(currentDate);
            eventsListViewItems.addAll(eventList);
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
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
            }
        }
    }
}
