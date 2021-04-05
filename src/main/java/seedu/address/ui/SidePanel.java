package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewContactsCommand;
import seedu.address.logic.commands.ViewTodayCommand;
import seedu.address.model.project.Project;

/**
 * Panel containing a project.
 */
public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";
    private static final String SELECTED_STYLE_CLASS = "selected";

    private ProjectListPanel projectListPanel;
    private MainWindow mainWindow;

    @FXML
    private StackPane projectsListPanelPlaceholder;
    @FXML
    private Button todayButton;
    @FXML
    private Button contactsButton;

    /**
     * Creates a {@code ProjectDisplayPanel} with the given {@code project} and {@code displayedIndex}.
     */
    public SidePanel(ObservableList<Project> projectList, MainWindow mainWindow) {
        super(FXML);

        addTodayButtonStyle();
        this.projectListPanel = new ProjectListPanel(projectList, mainWindow);
        this.mainWindow = mainWindow;
        this.projectsListPanelPlaceholder.getChildren().add(projectListPanel.getRoot());
    }

    /**
     * Selects a project in the {@code ListView} at a specific index.
     *
     * @param index Index to select.
     */
    public void selectProject(Index index) {
        projectListPanel.selectProject(index);
    }

    /**
     * Opens contacts panel.
     */
    @FXML
    public void openContactsPanel() {
        mainWindow.setFeedbackToUser(ViewContactsCommand.MESSAGE_SUCCESS);
        clearButtonStyles();
        addContactButtonStyle();
        mainWindow.resetContactsList();
        mainWindow.displayContacts();
    }

    /**
     * Opens today panel.
     */
    @FXML
    public void openTodayPanel() {
        mainWindow.setFeedbackToUser(ViewTodayCommand.MESSAGE_SUCCESS);
        clearButtonStyles();
        addTodayButtonStyle();
        mainWindow.displayToday();
    }

    /**
     * Clears currently selected item from {@code ProjectListPanel}
     */
    public void clearSelection() {
        projectListPanel.clearSelection();
    }

    /**
     * Resets all button styles to default.
     */
    public void clearButtonStyles() {
        todayButton.getStyleClass().remove(SELECTED_STYLE_CLASS);
        contactsButton.getStyleClass().remove(SELECTED_STYLE_CLASS);
    }

    /**
     * Change the background colour of today button to the current accent color value.
     */
    public void addTodayButtonStyle() {
        todayButton.getStyleClass().add(SELECTED_STYLE_CLASS);
    }

    /**
     * Change the background colour of contacts button to the current accent color value.
     */
    public void addContactButtonStyle() {
        contactsButton.getStyleClass().add(SELECTED_STYLE_CLASS);
    }
}
