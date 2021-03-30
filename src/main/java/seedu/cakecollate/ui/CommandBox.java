package seedu.cakecollate.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.cakecollate.logic.commands.CommandResult;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private ArrayList<String> userInputs = new ArrayList<>();
    private int userInputsIndex = 0;
    private boolean firstDecrementAfterUserInput = true;

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

    public void updateUserInputs(String input) {
        userInputs.add(input);
        incrementUserInputsIndex();
        userInputsIndex = userInputs.size() - 1;
        firstDecrementAfterUserInput = true;
    }

    private void incrementUserInputsIndex() {
        if (userInputsIndex + 1 < userInputs.size() && userInputsIndex < userInputs.size()) {
            userInputsIndex++;
        }
    }

    private void decrementUserInputsIndex() {
        if (!firstDecrementAfterUserInput && userInputsIndex > 0) {
            userInputsIndex--;
        }
        firstDecrementAfterUserInput = false;
    }

    public Optional<String> getPreviousInput() {
        decrementUserInputsIndex();
        Optional<String> previous = Optional.ofNullable(input());
        return previous;
    }

    public Optional<String> getNextInput() {
        incrementUserInputsIndex();
        Optional<String> next = Optional.ofNullable(input());
        return next;
    }

    private String input() {
        String output = null;
        if (userInputsIndex > -1 && userInputsIndex < userInputs.size()) {
            output = userInputs.get(userInputsIndex);
        }
        return output;
    }

    public TextField getCommandTextField() {
        return commandTextField;
    }

    public String getTextInCommandTextField() {
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
         * @see seedu.cakecollate.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
