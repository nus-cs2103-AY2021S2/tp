package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commandhistory.CommandHistorySelector;
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
    private final CommandHistorySelector commandHistorySelector;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor} and {@code CommandHistorySelector}.
     * {@code CommandExecutor} and {@code CommandHistorySelector} must be non-null.
     *
     * @throws NullPointerException If either parameter is null.
     */
    public CommandBox(CommandExecutor commandExecutor, CommandHistorySelector commandHistorySelector) {
        super(FXML);
        requireAllNonNull(commandExecutor, commandHistorySelector);
        this.commandExecutor = commandExecutor;
        this.commandHistorySelector = commandHistorySelector;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        assert commandTextField != null && commandExecutor != null;
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            showCommand("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles keyboard input when {@code CommandBox} is focused.
     */
    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        assert event != null;

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
        assert commandHistorySelector != null;
        String cmd = commandHistorySelector.selectNext().orElse("");
        showCommand(cmd);
    }

    /**
     * Selects the previous command in history, if any, then displays it.
     */
    private void selectPreviousCommand() {
        assert commandHistorySelector != null;
        String cmd = commandHistorySelector.selectPrevious().orElse("");
        showCommand(cmd);
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        assert commandTextField != null && commandTextField.getStyleClass() != null;
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        assert commandTextField != null && commandTextField.getStyleClass() != null;
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Displays the given command string in the command box.
     */
    private void showCommand(String cmd) {
        assert commandTextField != null;
        commandTextField.setText(cmd);
        commandTextField.positionCaret(commandTextField.getText().length());
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
