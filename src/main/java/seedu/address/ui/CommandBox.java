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

    /*
     * TODO: the code below can be more comprehensive
     *
     * Intended Idea:
     * E.g "edit blah blah blah <index>": on UP/Down KeyEvents, ONLY trigger <index> to change,
     * Same concept can be applied for other commands.
     * "Last value change only"
     *
     * For now,
     * - Current implementation does not factor in if the command is correct
     * - Current implementation only checks the first keyword after splitting by space character
     * - Currently only works on "Delete"
     *
     * Can consider having an implementation where only the index changes
     */
    /**
     * Sets the text after appending an index.
     *
     * @param index to accept user entered index.
     */
    public void setAndAppendIndex(String index) {
        String firstCommand = commandTextField.getText().split(" ")[0];
        if (firstCommand.equals(DeleteCommand.COMMAND_WORD)) {
            commandTextField.setText(firstCommand + " " + index);
        }
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
