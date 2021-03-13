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
        eventListView.setCellFactory(listView -> new ProjectDisplayPanel.PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Event} using an {@code EventDisplayCard}.
     */
    class PersonListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventDisplayCard(event, getIndex() + 1).getRoot());
            }
        }
    }
}
