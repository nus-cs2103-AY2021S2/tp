package seedu.address.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, ke-> {
            KeyboardShortcuts.matchAndSet(this.commandTextField, ke);
        });
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

    private static class KeyboardShortcuts {

        private static final KeyCombination CTRL_F = new KeyCodeCombination(KeyCode.F,
                KeyCombination.CONTROL_DOWN);

        private static final KeyCombination CTRL_L = new KeyCodeCombination(KeyCode.L,
                KeyCombination.CONTROL_DOWN);

        private static final KeyCombination CTRL_A = new KeyCodeCombination(KeyCode.A,
                KeyCombination.CONTROL_DOWN);

        private static final KeyCombination CTRL_D = new KeyCodeCombination(KeyCode.D,
                KeyCombination.CONTROL_DOWN);

        private static final KeyCombination CTRL_E = new KeyCodeCombination(KeyCode.E,
                KeyCombination.CONTROL_DOWN);

        private static final KeyCombination CTRL_S = new KeyCodeCombination(KeyCode.S,
                KeyCombination.CONTROL_DOWN);

        //@@author swayongshen-reused
        //https://stackoverflow.com/questions/25397742/javafx-keyboard-event-shortcut-key

        /**
         * Handles the KeyEvent which happens when keyboard keys are pressed. Checks if the keys pressed
         * are part of the pre-defined shortcuts and fill the CommandBox's text field appropriately.
         * @param textField CommandBox's text field
         * @param keyEvent the KeyEvent which contains information about what keys are pressed.
         */
        public static void matchAndSet(TextField textField, KeyEvent keyEvent) {
            if (CTRL_F.match(keyEvent)) {
                Platform.runLater(() -> textField.setText("find"));
            } else if (CTRL_L.match(keyEvent)) {
                Platform.runLater(() -> textField.setText("list"));
            } else if (CTRL_A.match(keyEvent)) {
                Platform.runLater(() -> textField.setText("add"));
            } else if (CTRL_D.match(keyEvent)) {
                Platform.runLater(() -> textField.setText("delete"));
            } else if (CTRL_E.match(keyEvent)) {
                Platform.runLater(() -> textField.setText("edit"));
            } else if (CTRL_S.match(keyEvent)) {
                Platform.runLater(() -> textField.setText("sort"));
            } else if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                Platform.runLater(() -> backspace(textField));
            }
            //Set cursor/caret to the end of text field
            Platform.runLater(() -> textField.positionCaret(textField.getLength()));
            //Prevent keyEvent from being re-handled.
            Platform.runLater(keyEvent::consume);
        }

        private static void backspace(TextField textField) {
            int currLength = textField.getLength();
            String backspaced = textField.getText(0, currLength);
            textField.setText(backspaced);
        }
    }
}
