package seedu.us.among.ui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.Request;
import seedu.us.among.logic.endpoint.exceptions.AbortRequestException;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private ResultDisplay resultDisplay;
    private Stage primaryStage;

    // Handle ctrl+c event for cancelling API calls
    private EventHandler<KeyEvent> keyEventHandler;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, ResultDisplay resultDisplay, Stage primaryStage) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.resultDisplay = resultDisplay;
        this.primaryStage = primaryStage;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        initialiseKeyEventHandler();
    }

    /**
     * Handles the command received
     *
     * @param commandText command received from user
     * @param isInThread boolean to flag if a command is being ran on a background thread
     */
    private void handleCommand(String commandText, boolean isInThread) {
        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
            //to-do remove illegal arg exception Jun Xiong and Tan Jin
        } catch (CommandException | ParseException | RequestException | IllegalArgumentException
                | AbortRequestException e) {
            setStyleToIndicateCommandFailure();
        } finally {
            if (isInThread) {
                unsetKeyEventHandler();
            }
            commandTextField.setDisable(false);
        }
    }

    /**
     * Creates thread to handle the command received
     *
     * @param commandText command received from user
     */
    private void handleCommandInNewThread(String commandText) {
        //handle API commands input in new thread
        Task<Void> task = new Task<>() {
            @Override public Void call() {
                handleCommand(commandText, true);
                //to select command box again after command execution in thread ends
                Platform.runLater(() -> {
                    commandTextField.requestFocus();
                    commandTextField.selectEnd();
                });
                return null;
            }
        };
        new Thread(task).start();
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        //prevent valid input spam
        commandTextField.setDisable(true);

        //reset timer for error gif (if any)
        resultDisplay.getErrorGifTimeline().stop();
        resultDisplay.getErrorPlaceholder().setVisible(false);

        String commandText = commandTextField.getText();

        if (commandText.startsWith("send ") || commandText.startsWith("run ")) {
            setKeyEventHandler();
            handleCommandInNewThread(commandText);
        } else if (!commandText.equals("")) {
            handleCommand(commandText, false);
        } else {
            commandTextField.setDisable(false);
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Closes httpClient when ctrl + c is pressed.
     *
     * @param event key event for pressed keys
     */
    public void closeHttpClient(KeyEvent event) throws IOException {
        KeyCombination kc = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        if (kc.match(event)) {
            Request.getHttpclient().close();
        }
    }

    /**
     * Initialises key handler to be method for handling ctrl + c input.
     */
    public void initialiseKeyEventHandler() {
        this.keyEventHandler = e -> {
            try {
                closeHttpClient(e);
            } catch (IOException ioException) {
                //logger.info("Unable to stop request: " + ioException.getMessage());
            }
        };
    }

    /**
     * Sets the key event handler to look out for key input of ctrl + c from user.
     */
    public void setKeyEventHandler() {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, this.keyEventHandler);
    }

    /**
     * Unsets the key event handler that looks out for key input of ctrl + c from user.
     */
    public void unsetKeyEventHandler() {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, this.keyEventHandler);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.us.among.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException,
                RequestException, AbortRequestException;
    }

}
