package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.project.Project;

/**
 * Panel containing a project.
 */
public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SidePanel.class);

    private ProjectListPanel projectListPanel;

    @FXML
    private StackPane projectsListPanelPlaceholder;

    /**
     * Creates a {@code ProjectDisplayPanel} with the given {@code project} and {@code displayedIndex}.
     */
    public SidePanel(ObservableList<Project> projectList, MainWindow mainWindow) {
        super(FXML);

        projectListPanel = new ProjectListPanel(projectList, mainWindow);
        projectsListPanelPlaceholder.getChildren().add(projectListPanel.getRoot());
    }

    /**
     * Selects a project in the {@code ListView} at a specific index.
     *
     * @param index Index to select.
     */
    public void selectProject(Index index) {
        projectListPanel.selectProject(index);
    }
}
