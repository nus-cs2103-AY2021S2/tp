package seedu.plan.ui;

import java.util.logging.Logger;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.plan.commons.core.GuiSettings;
import seedu.plan.commons.core.LogsCenter;
import seedu.plan.logic.Logic;
import seedu.plan.logic.commands.AddPlanCommand;
import seedu.plan.logic.commands.CommandResult;
import seedu.plan.logic.commands.InfoCommand;
import seedu.plan.logic.commands.ListCommand;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.logic.parser.exceptions.ParseException;

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
    private PlanListPanelWithTable planListPanelWithTable;
    private PlanListPanel planListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private InfoListPanel infoListPanel;
    private InfoListPanel singleFoundModulePanel;

    // Decides what panellist is shown
    private StringProperty displayPanelList;


    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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

        this.displayPanelList = logic.getDisplayPanelListCommand();

        displayPanelList.addListener(new ChangeListener<String>() {

            /**if your command needs the plane to change, you can add it here
             * Follow this sequence:
             *  1. create a panel class for your info e.g. planlistpanel
             *  2. create a card class for you info e.g. plancard
             *  3. create observable list and add all the information you want to show
             *  4. add model.setCurrentCommand(COMMAND_WORD); to your command when it succeeds
             *  5. add the command in here
             * */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch(newValue) {
                    case ListCommand.COMMAND_WORD:
                        personListPanelPlaceholder.getChildren().setAll(planListPanelWithTable.getRoot());
                        break;
                    case InfoCommand.COMMAND_WORD:
                        personListPanelPlaceholder.getChildren().setAll(infoListPanel.getRoot());
                        break;
                    case AddPlanCommand.COMMAND_WORD:
                        personListPanelPlaceholder.getChildren().setAll(planListPanel.getRoot());
                        break;
                    case InfoCommand.COMMAND_WORD_SINGLE_MODULE:
                        personListPanelPlaceholder.getChildren().setAll(singleFoundModulePanel.getRoot());
                        break;
                    default:
                        personListPanelPlaceholder.getChildren().setAll(planListPanel.getRoot());
                }
            }
        });
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
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
        planListPanel = new PlanListPanel(logic.getFilteredPersonList());
        planListPanelWithTable = new PlanListPanelWithTable(logic.getFilteredPersonList());

        personListPanelPlaceholder.getChildren().add(planListPanel.getRoot());

        infoListPanel = new InfoListPanel(logic.getModuleInfoList());

        singleFoundModulePanel = new InfoListPanel(logic.getSingleModuleInfoList());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getModulePlannerFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

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
        // disable resize
        primaryStage.setResizable(false);
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

    public PlanListPanel getPersonListPanel() {
        return planListPanel;
    }

    public PlanListPanelWithTable getPersonListPanelWithTable() {
        return planListPanelWithTable;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
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

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
