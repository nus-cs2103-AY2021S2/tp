package seedu.address.ui;

import javafx.collections.FXCollections;
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

    private final ObservableList<String> comLog;
    private int comLogIndicator;

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

        comLog = FXCollections.observableArrayList();
        comLogIndicator = 0;
        addListener();
    }

    private void addListener() {
        commandTextField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            KeyCombination keyCombination_add = new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_ANY);
            KeyCombination keyCombination_edit = new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_ANY);
            KeyCombination keyCombination_delete = new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_ANY);

            KeyCombination keyCombination_a = new KeyCodeCombination(KeyCode.A);
            KeyCombination keyCombination_e = new KeyCodeCombination(KeyCode.E);
            KeyCombination keyCombination_d = new KeyCodeCombination(KeyCode.D);


            if (keyCombination_a.match(event) || keyCombination_e.match(event) || keyCombination_d.match(event)) {
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                if (comLogIndicator + 1 <= comLog.size()) {
                    if (comLogIndicator + 1 == comLog.size()) {
                        comLogIndicator++;
                        commandTextField.setText("");
                        commandTextField.end();
                    } else {
                        comLogIndicator++;
                        commandTextField.setText(comLog.get(comLogIndicator));
                        commandTextField.end();
                    }
                }
            } else if (event.getCode().equals(KeyCode.UP)) {
                if (comLogIndicator - 1 >= 0) {
                    comLogIndicator--;
                    commandTextField.setText(comLog.get(comLogIndicator));
                    commandTextField.end();
                }
            } else if (keyCombination_add.match(event)) {
                commandTextField.setText("add n/ s/ c/ r/ t/ d/");
                commandTextField.requestFocus();
                commandTextField.positionCaret(5);
                commandTextField.end();
            } else if (keyCombination_edit.match(event)) {
                commandTextField.setText("edit [INDEX] n/ s/ c/ r/ t/ d/");
                commandTextField.requestFocus();
                commandTextField.end();
            } else if (keyCombination_delete.match(event)) {
                commandTextField.setText("delete [INDEX]");
                commandTextField.requestFocus();
                commandTextField.end();
            }
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

        comLog.add(commandText);
        comLogIndicator = comLog.size();

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
