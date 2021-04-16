package seedu.address.ui;

import java.util.function.Consumer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
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
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
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
     * Sets the commandTextField value.
     *
     * @param value to set.
     */
    public void setTextValue(String value) {
        commandTextField.setText(value);
        commandTextField.requestFocus();
        commandTextField.end();
    }

    /**
     * Sets the callback function for a keypress.
     *
     * @param callback to accept user entered text.
     */
    public void setKeyUpCallback(Consumer<String> callback) {
        commandTextField.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (keyEvent.getCode() != KeyCode.TAB) {
                callback.accept(commandTextField.getText());
            }
        });
    }

    /**
     * Sets the textfield after appending an index.
     *
     * @param index to accept user entered index.
     */
    public void setAndAppendIndex(String index) {
        String[] substrings = commandTextField.getText().split(" ");
        String firstCommand = substrings[0];

        if (firstCommand.equals(DeleteCommand.COMMAND_WORD) || firstCommand.equals(EditCommand.COMMAND_WORD)) {
            this.setTextValue(firstCommand + " " + index);
        }
    }

    /**
     * Sets the textfield after appending a flag
     *
     * @param flag to accept user entered flag
     */
    public void setAndAppendFlag(String flag) {
        String existingText = commandTextField.getText().trim();
        this.setTextValue(existingText + " " + flag);
    }

    /**
     * Returns the text in JavaFX textField.
     *
     * @return text in JavaFX textField
     */
    public String getTextFieldText() {
        return commandTextField.getText();
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

    /**
     * Clears command box text field.
     */
    public void clearText() {
        commandTextField.setText("");
    }

}
