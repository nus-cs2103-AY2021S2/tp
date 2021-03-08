package seedu.us.among.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.logic.Logic;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    // Tracks and shows progress of user commands
    private static boolean isInProgress = false;
    private String progressMessage = "Processing Command";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private EndpointListPanel endpointListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane endpointListPanelPlaceholder;

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
        endpointListPanel = new EndpointListPanel(logic.getFilteredEndpointList());
        endpointListPanelPlaceholder.getChildren().add(endpointListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getEndpointListFilePath());
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
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            Platform.runLater(() -> {
                helpWindow.show();
            });
        } else {
            Platform.runLater(() -> {
                helpWindow.focus();
            });
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
        Platform.runLater(() -> {
            helpWindow.hide();
            primaryStage.hide();
        });
    }

    public EndpointListPanel getEndpointListPanel() {
        return endpointListPanel;
    }

    /**
     * Updates the progress message.
     */
    private void updateProgress() {
        if (progressMessage.length() < 21) {
            progressMessage += ".";
        } else {
            progressMessage = "Processing Command";
        }
        resultDisplay.setFeedbackToUser(progressMessage);
    }

    /**
     * Gets the status for command execution.
     * @return true or false to indicate if command execution is still in progress
     */
    public static boolean getProgress() {
        return isInProgress;
    }

    /**
     * Continuously update the UI to show progress to user.
     * @return thread that is updating the UI
     */
    public Thread showProgress() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = () -> updateProgress();
                while (MainWindow.getProgress()) {
                    Platform.runLater(updater);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //try-catch to allow thread to sleep
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        isInProgress = true;
        Thread thread = showProgress();
        try {
            CommandResult commandResult = logic.execute(commandText);
            isInProgress = false;
            try {
                thread.join();
            } catch (InterruptedException interruptedException) {
                throw new CommandException("Placeholder");
            }
            logger.info("Result: " + commandResult.getFeedbackToUser());
            Platform.runLater(() -> {
                if (commandResult.isApiResponse()) {
                    resultDisplay.setApiFeedbackToUser(commandResult.getFeedbackToUser(), commandResult.getEndpoint());
                } else {
                    resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
                }
            });

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            isInProgress = false;
            try {
                thread.join();
            } catch (InterruptedException interruptedException) {
                throw new CommandException("Placeholder");
            }
            logger.info("Invalid command: " + commandText);
            Platform.runLater(() -> resultDisplay.setFeedbackToUser(e.getMessage()));
            throw e;
        }
    }
}
