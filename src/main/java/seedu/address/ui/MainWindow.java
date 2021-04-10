package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final int MENU_TAB_INDEX = 0;
    private static final int ORDER_TAB_INDEX = 1;
    private static final int INVENTORY_TAB_INDEX = 2;

    private static double xOffset = 0;
    private static double yOffset = 0;

    private static final int DRAGGABLE_MARGIN = 30;
    private Boolean isResizing = false;
    private double resizeX;
    private double resizeY;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private MenuListPanel menuListPanel;
    private InventoryListPanel inventoryListPanel;
    private OrderListPanel orderListPanel;

    @FXML
    private ToggleGroup componentTabGroup;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuSettings;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private VBox personList;

    @FXML
    private VBox componentList;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane menuListPanelPlaceholder;

    @FXML
    private StackPane inventoryListPanelPlaceholder;

    @FXML
    private StackPane orderListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private HBox componentTabs;

    @FXML
    private ToggleButton menuTab;

    @FXML
    private ToggleButton orderTab;

    @FXML
    private ToggleButton inventoryTab;

    @FXML
    private HBox statusbarPlaceholder;

    private final EventHandler<ActionEvent> handleTabSelection = event -> {
        int selectedIndex = componentTabGroup.getToggles().indexOf(componentTabGroup.getSelectedToggle());
        componentTabGroup.selectToggle(null);
        Object object = event.getSource();

        if (object instanceof ToggleButton) {
            ToggleButton tab = (ToggleButton) object;
            tab.setSelected(true);
            try {
                switch(selectedIndex) {
                case MENU_TAB_INDEX:
                    executeCommand("menu list");
                    break;
                case ORDER_TAB_INDEX:
                    executeCommand("order list");
                    break;
                case INVENTORY_TAB_INDEX:
                    executeCommand("inventory list");
                    break;
                default:
                }
            } catch (CommandException | ParseException e) {
                resultDisplay.setFeedbackToUser(e.getMessage());
            }
        }
    };

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        setTransparentWindow();
        setAccelerators();
        setComponentTabsAction();

        menuSettings.setGraphic(new ImageView("images/settings.png"));

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Removes windows border.
     */
    private void setTransparentWindow() {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        setDraggableWindow();
        setResizableWindow();
    }

    /**
     * Configures Menu Bar to be draggable to drag the window.
     */
    private void setDraggableWindow() {
        menuBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        menuBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });
    }

    /**
     * Configures the right corner of status bar to resize the window.
     */
    private void setResizableWindow() {
        setOnMouseEntered();
        setOnMousePressed();
        setOnMouseDragged();
        setOnMouseExited();
    }

    /**
     * Changes mouse cursor to resize cursor when entered resizing zone
     */
    private void setOnMouseEntered() {
        statusbarPlaceholder.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.getScene().setCursor(Cursor.NW_RESIZE);
            }
        });
    }

    /**
     * Get the current coordinates of the cursor pressed.
     */
    private void setOnMousePressed() {
        statusbarPlaceholder.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (isDraggable(event)) {
                    isResizing = true;
                    resizeX = primaryStage.getWidth() - event.getSceneX();
                    resizeY = primaryStage.getHeight() - event.getSceneY();
                } else {
                    isResizing = false;
                }
            }
        });
    }

    /**
     * Calculates and resize the window based on the coordinates given by  {@link #setOnMousePressed()}
     */
    private void setOnMouseDragged() {
        statusbarPlaceholder.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (isResizing) {
                    primaryStage.setWidth(event.getSceneX() + resizeX);
                    primaryStage.setHeight(event.getSceneY() + resizeY);
                }
            }
        });
    }

    private void setOnMouseExited() {
        statusbarPlaceholder.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    /**
     * Checks if the coordinates of the mouse position is within draggable margin
     * @param  event OnMouseClick Event
     * @return true if coordinates is within draggable margin
     */
    private boolean isDraggable(MouseEvent event) {
        return event.getSceneX() > primaryStage.getWidth() - DRAGGABLE_MARGIN
                && event.getSceneY() > primaryStage.getHeight() - DRAGGABLE_MARGIN;
    }

    private void setComponentTabsAction() {
        menuTab.setOnAction(handleTabSelection);
        orderTab.setOnAction(handleTabSelection);
        inventoryTab.setOnAction(handleTabSelection);
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(exitMenuItem, KeyCombination.valueOf("F2"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        personList.getChildren().clear();
        personList.getChildren().add(personListPanelPlaceholder);

        menuListPanel = new MenuListPanel(logic.getFilteredDishList());
        menuListPanelPlaceholder.getChildren().add(menuListPanel.getRoot());
        componentList.getChildren().clear();
        componentList.getChildren().add(menuListPanelPlaceholder);
        componentList.getChildren().add(componentTabs);
        menuTab.setSelected(true);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        resultDisplay.setFeedbackToUser(Messages.MESSAGE_WELCOME);

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        try {
            Desktop.getDesktop().browse(new URL(HelpWindow.USERGUIDE_URL).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            assert commandResult.type()
                    != CommandResult.CRtype.NONE : "Command Result is not supposed to be a help or exit command";
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            switch (commandResult.type()) {
            case PERSON:
                updateCompList(personList, personListPanel,
                        new PersonListPanel(logic.getFilteredPersonList()), personListPanelPlaceholder, null);
                break;

            case DISH:
                updateCompList(componentList, menuListPanel,
                        new MenuListPanel(logic.getFilteredDishList()), menuListPanelPlaceholder, menuTab);
                break;

            case INGREDIENT:
                updateCompList(componentList, inventoryListPanel,
                        new InventoryListPanel(logic.getFilteredInventoryList()),
                        inventoryListPanelPlaceholder, inventoryTab);
                break;

            case ORDER:
                updateCompList(componentList, orderListPanel,
                        new OrderListPanel(logic.getFilteredOrderList()), orderListPanelPlaceholder, orderTab);
                break;

            default:
                break;
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void updateCompList(VBox list, UiPart<Region> panel, UiPart<Region> newPanel,
                                StackPane panelPlaceholder, ToggleButton tab) {
        list.getChildren().clear();
        panelPlaceholder.getChildren().clear();
        panel = newPanel;
        panelPlaceholder.getChildren().add(panel.getRoot());
        list.getChildren().add(panelPlaceholder);
        if (list.getId().equalsIgnoreCase("componentList")) {
            list.getChildren().add(componentTabs);
            tab.setSelected(true);
        }
    }
}
