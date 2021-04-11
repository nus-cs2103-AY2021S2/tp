package seedu.cakecollate.ui;

import static seedu.cakecollate.logic.commands.HelpCommand.SHOWING_RETURN_MESSAGE;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.logic.Logic;
import seedu.cakecollate.logic.commands.CommandResult;
import seedu.cakecollate.logic.commands.HelpCommand;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private boolean inHelp = false;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private Panel orderPanel;
    private Panel helpPanel;
    private Panel orderItemPanel;
    private Button helpPanelToMain;
    private CommandBox commandBox;
    private Node models;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane orderItemTablePlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private HBox modelBox;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) throws CommandException, ParseException {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        primaryStage.setMaximized(true);
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        initialiseHelpPanelAndButton();
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
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getCakeCollateFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        commandBoxArrowShortcut();

        orderPanel = new OrderListPanel(logic.getFilteredOrderList());
        listPanelPlaceholder.getChildren().add(orderPanel.getRoot());

        orderItemPanel = new OrderItemPanel(logic.getFilteredOrderItemsList());
        orderItemTablePlaceholder.getChildren().add(orderItemPanel.getRoot());
    }

    void updateDeliveryStatuses() throws ParseException, CommandException {
        String deliveryStatus = logic.updateDeliveryStatus();
        if (!deliveryStatus.isEmpty()) {
            executeCommand(logic.updateDeliveryStatus());
        }
    }

    void initialiseHelpPanelAndButton() {
        helpPanel = new HelpListPanel(HelpCommand.getListOfCommands());
        helpPanelToMain = new Button("Return to the order list");
        helpPanelToMain.setOnAction(event -> {
            resetMainWindow();
            logger.info("Result: " + SHOWING_RETURN_MESSAGE);
            resultDisplay.setFeedbackToUser(SHOWING_RETURN_MESSAGE);
        });
    }

    void commandBoxArrowShortcut() {
        commandBox.getCommandTextField().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.UP)) {
                commandBox.setCommandTextField(commandBox.getPreviousInput());
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                commandBox.setCommandTextField(commandBox.getNextInput());
            } else if (event.getCode().equals(KeyCode.SHIFT)) {
                commandBox.updateShiftEntered(true);
            } else if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                commandBox.handleBackSpace();
            }

            if (!event.getCode().equals(KeyCode.SHIFT)) {
                commandBox.updateShiftEntered(false);
            }
        });
    }

    /**
     * Transitions from the help window to the the main order list.
     */
    public void resetMainWindow() {
        replaceHelpPanelWithModels();
        removeHelpButtonFromDisplay();

        logger.info("Result: " + SHOWING_RETURN_MESSAGE);

        inHelp = false;
    }

    void replaceHelpPanelWithModels() {
        modelBox.getChildren().remove(0);
        modelBox.getChildren().add(models);
    }

    void removeHelpButtonFromDisplay() {
        resultDisplayPlaceholder.getChildren().remove(1);
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
        if (!inHelp) {
            inHelp = true;
            removeModelsFromDisplay();
            replaceModelsWithHelpPanel();
            addHelpButtonToDisplay();
        }
    }

    private void removeModelsFromDisplay() {
        models = modelBox.getChildren().get(0);
        modelBox.getChildren().remove(0);
    }

    private void replaceModelsWithHelpPanel() {
        helpPanel.getRoot().setPrefWidth(modelBox.getWidth());
        modelBox.getChildren().add(helpPanel.getRoot());
    }

    private void addHelpButtonToDisplay() {
        helpPanelToMain.setPrefWidth(resultDisplayPlaceholder.getWidth());
        resultDisplayPlaceholder.getChildren().add(helpPanelToMain);
        StackPane.setAlignment(helpPanelToMain, Pos.BOTTOM_CENTER);
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
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.cakecollate.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            commandBox.updateUserInputs(commandText);
            CommandResult commandResult = logic.execute(commandText);
            String result = commandResult.getFeedbackToUser();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (inHelp && !commandResult.isShowHelp()) {
                resetMainWindow();
            }

            resultDisplay.setFeedbackToUser(result);
            logger.info("Result: " + result);

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
