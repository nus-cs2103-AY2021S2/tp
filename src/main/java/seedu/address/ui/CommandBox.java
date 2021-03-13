package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextArea commandTextArea;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextArea.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        // remove ENTER event to prevent new lines on ENTER, then handle command
        commandTextArea.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    event.consume();
                    handleCommandEntered(event);
                }
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            int currentCaretPosition = commandTextArea.getCaretPosition();
            String commandText = commandTextArea.getText();

            // shift+enter makes a newline anywhere
            if (keyEvent.isShiftDown()) {
                commandTextArea.setText(commandText.substring(0, currentCaretPosition)
                        + System.getProperty("line.separator") + commandText.substring(currentCaretPosition));
                commandTextArea.positionCaret(currentCaretPosition + 1);
                return;
            }

            if (commandText.equals("")) {
                return;
            }
            // error message wrong when multiline

            // remove trailing newlines
            commandTextArea.setText(commandText.replaceAll("\n+$", ""));
            commandTextArea.positionCaret(currentCaretPosition);

            try {
                commandExecutor.execute(commandText);
                commandTextArea.setText("");
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextArea.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextArea.getStyleClass();

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
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
