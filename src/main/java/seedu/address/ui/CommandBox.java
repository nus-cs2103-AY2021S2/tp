package seedu.address.ui;

import java.util.function.Supplier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.util.MathUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final Supplier<ReadOnlyCommandHistory> commandHistorySupplier;

    @FXML
    private TextField commandTextField;
    private int commandHistoryIndex;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor} and command history supplier.
     */
    public CommandBox(CommandExecutor commandExecutor, Supplier<ReadOnlyCommandHistory> commandHistorySupplier) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandHistorySupplier = commandHistorySupplier;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
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
            commandHistoryIndex = commandHistorySupplier.get().size();
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles keyboard input when {@code CommandBox} is focused.
     */
    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            event.consume();
            selectPreviousCommand();
            break;

        case DOWN:
            event.consume();
            selectNextCommand();
            break;

        default:
            break;
        }
    }

    /**
     * Selects the next command in history, if any, then displays it.
     */
    private void selectNextCommand() {
        final ReadOnlyCommandHistory history = commandHistorySupplier.get();
        final int size = history.size();
        commandHistoryIndex = MathUtil.clamp(commandHistoryIndex + 1, 0, size);

        showCommand(history, size, commandHistoryIndex);
    }

    /**
     * Selects the previous command in history, if any, then displays it.
     */
    private void selectPreviousCommand() {
        final ReadOnlyCommandHistory history = commandHistorySupplier.get();
        final int size = history.size();
        commandHistoryIndex = MathUtil.clamp(commandHistoryIndex - 1, 0, size);

        showCommand(history, size, commandHistoryIndex);
    }

    /**
     * Displays the historical command at the given index, if any.
     */
    private void showCommand(ReadOnlyCommandHistory history, int size, int index) {
        String cmd = index < 0 || index >= size
                ? ""
                : history.get(index).value;
        commandTextField.setText(cmd);
        commandTextField.positionCaret(commandTextField.getText().length());
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
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
