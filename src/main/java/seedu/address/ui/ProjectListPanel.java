package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.project.Project;

/**
 * Panel containing the list of projects.
 */
public class ProjectListPanel extends UiPart<Region> {
    private static final String FXML = "ProjectListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProjectListPanel.class);

    @FXML
    private ListView<Project> projectListView;

    /**
     * Creates a {@code ProjectListPanel} with the given {@code ObservableList}.
     */
    public ProjectListPanel(ObservableList<Project> projectList, MainWindow mainWindow) {
        super(FXML);
        projectListView.setItems(projectList);
        projectListView.setCellFactory(listview -> new ProjectListViewCell());
        projectListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {
            @Override
            public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
                mainWindow.displayProject(newValue);
            }
        });
    }

    /**
     * Clears currently selected item from {@code ProjectListPanel}
     */
    public void clearSelection() {
        projectListView.getSelectionModel().clearSelection();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Project} using a {@code ProjectCard}.
     */
    class ProjectListViewCell extends ListCell<Project> {
        @Override
        protected void updateItem(Project project, boolean empty) {
            super.updateItem(project, empty);

            if (empty || project == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProjectCard(project, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Selects a project in the {@code ListView} at a specific index.
     *
     * @param index Index to select.
     */
    public void selectProject(Index index) {
        requireNonNull(index);
        projectListView.getSelectionModel().select(index.getZeroBased());
    }
}
