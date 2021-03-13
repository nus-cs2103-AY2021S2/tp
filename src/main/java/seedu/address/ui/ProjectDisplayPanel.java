package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.task.repeatable.Event;

/**
 * Panel containing a project.
 */
public class ProjectDisplayPanel extends UiPart<Region> {
    private static final String FXML = "ProjectDisplayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProjectDisplayPanel.class);

    @FXML
    private Label projectName;

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private ListView<Person> participantListView;

    /**
     * Creates a {@code ProjectDisplayPanel} with the given {@code project} and {@code displayedIndex}.
     */
    public ProjectDisplayPanel() {
        super(FXML);
    }

    /**
     * Displays a project in the {@code ProjectDisplayPanel}.
     * @param project Project to display.
     */
    public void displayProject(Project project, Index index) {
        this.projectName.setText(index.getOneBased() + ". " + project.getProjectName().toString());

        eventListView.setItems(new FilteredList<>(project.getEvents().getAsObservableList()));
        eventListView.setCellFactory(listView -> new ProjectDisplayPanel.EventListViewCell());

        participantListView.setItems(new FilteredList<>(project.getParticipants().getAsObservableList()));
        participantListView.setCellFactory(listView -> new ProjectDisplayPanel.ParticipantListViewCell());
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

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonDisplayCard}.
     */
    class ParticipantListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
