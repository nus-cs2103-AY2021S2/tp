package seedu.taskify.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.taskify.commons.core.GuiSettings;
import seedu.taskify.commons.core.LogsCenter;
import seedu.taskify.logic.Logic;
import seedu.taskify.logic.commands.CommandResult;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;
    private ExpiredTaskListPanel expiredTaskListPanel;
    private CompletedTaskListPanel completedTaskListPanel;
    private UncompletedTaskListPanel uncompletedTaskListPanel;
    private TodaysTaskListPanel todaysTaskListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane todaysTaskListPanelPlaceholder;

    @FXML
    private StackPane expiredTaskListPanelPlaceholder;

    @FXML
    private StackPane completedTaskListPanelPlaceholder;

    @FXML
    private StackPane uncompletedTaskListPanelPlaceholder;

    @FXML
    private TabPane tabsPane;


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

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
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
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTaskifyFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        expiredTaskListPanel = new ExpiredTaskListPanel(logic.getExpiredFilteredTaskList());
        expiredTaskListPanelPlaceholder.getChildren().add(expiredTaskListPanel.getRoot());

        completedTaskListPanel = new CompletedTaskListPanel(logic.getCompletedFilteredTaskList());
        completedTaskListPanelPlaceholder.getChildren().add(completedTaskListPanel.getRoot());

        uncompletedTaskListPanel = new UncompletedTaskListPanel(logic.getUncompletedFilteredTaskList());
        uncompletedTaskListPanelPlaceholder.getChildren().add(uncompletedTaskListPanel.getRoot());

        todaysTaskListPanel = new TodaysTaskListPanel(logic.getTodaysFilteredTaskList());
        todaysTaskListPanelPlaceholder.getChildren().add(todaysTaskListPanel.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        logger.info("Filled up UI components");
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
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Tab switching idea adapted from
     * https://github.com/AY2021S1-CS2103-T14-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java
     */

    @FXML
    private void switchTab(int tabNumber) {
        tabsPane.getSelectionModel().getSelectedItem().setDisable(true);
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
        tabsPane.getSelectionModel().select(tabNumber);
        tabsPane.getSelectionModel().getSelectedItem().setDisable(false);
        logger.info("Switched Tab");
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
        logger.info("Exit Taskify");
    }


    /**
     * Executes the command and returns the result.
     *
     * @see seedu.taskify.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            for (int i = 0; i < CommandResult.getTabBoolean().size(); i++) {
                if (CommandResult.getTabBoolean().get(i)) {
                    switchTab(i);
                }
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
