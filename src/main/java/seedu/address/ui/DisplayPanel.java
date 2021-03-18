package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DisplayPanel extends UiPart<Region> {

    private static final String FXML = "DisplayPanel.fxml";

    private Logic logic;
    private ModuleListPanel moduleListPanel;
    private PersonListPanel personListPanel;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane moduleListPanelPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;


    @FXML
    private Tab modules;

    @FXML
    private Tab contacts;

    /**
     * Creates a display panel to hold all feature related contents
     * @param logic the logic object that manages the ui and the data
     */
    public DisplayPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillInnerPart();
        listenerOnChange();
    }

    private void listenerOnChange() {
        addTabPaneListener();
    }

    private void addTabPaneListener() {
        tabPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            tabPane.setTabMinWidth((tabPane.getWidth() - 50) / tabPane.getTabs().size());
            tabPane.setTabMaxWidth((tabPane.getWidth() - 70) / tabPane.getTabs().size());
        });

    }

    private void fillInnerPart() {
        moduleListPanel = new ModuleListPanel(logic.getFilteredModuleList());
        moduleListPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

    }

    public void showModules() {
        tabPane.getSelectionModel().select(modules);
    }

    public void showContacts() {
        tabPane.getSelectionModel().select(contacts);
    }

}
