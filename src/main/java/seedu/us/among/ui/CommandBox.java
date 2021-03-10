package seedu.us.among.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.exceptions.CommandException;
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

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, ResultDisplay resultDisplay) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.resultDisplay = resultDisplay;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the command received
     *
     * @param commandText command received from user
     */
    private void handleCommand(String commandText) {
        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
            //to-do remove illegal arg exception Jun Xiong and Tan Jin
        } catch (CommandException | ParseException | RequestException | IllegalArgumentException e) {
            setStyleToIndicateCommandFailure();
        } finally {
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
                handleCommand(commandText);
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
            handleCommandInNewThread(commandText);
        } else if (!commandText.equals("")) {
            handleCommand(commandText);
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
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.us.among.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException, RequestException;
    }

}
