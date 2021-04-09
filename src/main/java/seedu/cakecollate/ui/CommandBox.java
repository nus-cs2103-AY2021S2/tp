package seedu.cakecollate.ui;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.cakecollate.logic.commands.CommandResult;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.logic.parser.exceptions.ParseException;

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

    private boolean isShiftEntered = false;

    private boolean firstUpEntered = true;
    private String firstUpEnteredString = "";

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

    /**
     * Adds the command typed in by the user to the array list userInputs.
     * @param input the command that the user has typed in.
     */
    public void updateUserInputs(String input) {
        userInputs.add(input);
        incrementUserInputsIndex();
        userInputsIndex = userInputs.size() - 1;
        updateVariablesOnNewCommand();
        positionCaretInTheEnd();
    }

    private void updateVariablesOnNewCommand() {
        firstDecrementAfterUserInput = true;
        firstUpEntered = true;
        firstUpEnteredString = "";
    }

    /**
     * Sets the input string as the text in the command box if it exists.
     * @param input The Optional of the string to be set in the command box if it exists.
     */
    public void setCommandTextField(Optional<String> input) {
        input.ifPresent(string -> getCommandTextField().setText(string));
        positionCaretInTheEnd();
    }

    /**
     * Gets the command text field.
     * @return The command text field.
     */
    public TextField getCommandTextField() {
        return commandTextField;
    }

    /**
     * If shift is entered right before the backspace, all the text is removed from the command box.
     */
    public void handleBackSpace() {
        if (isShiftEntered) {
            getCommandTextField().setText("");
        }
    }

    /**
     * Updates the boolean value of isShiftEntered depending on the user input.
     * @param isShiftEntered True if shift is entered, and false otherwise.
     */
    public void updateShiftEntered(boolean isShiftEntered) {
        this.isShiftEntered = isShiftEntered;
    }

    /**
     * Navigates to the previous string in the userInputs array list if it exists.
     * @return Optional of the previous string if it exists.
     */
    public Optional<String> getPreviousInput() {
        if (firstUpEntered) {
            firstUpEnteredString = getTextInCommandTextField();
            firstUpEntered = false;
        } else if (userInputsIndex == userInputs.size()) {
            firstUpEnteredString = getTextInCommandTextField();
        }

        decrementUserInputsIndex();
        return Optional.ofNullable(input());
    }

    /**
     * Navigates to the next string in the userInputs array list if it exists.
     * @return Optional of the next string if it exists.
     */
    public Optional<String> getNextInput() {
        incrementUserInputsIndex();
        return Optional.ofNullable(input());
    }

    /**
     * Returns the user input in the position userInputsIndex from the array list if it exists, and null otherwise.
     * @return
     */
    private String input() {
        String output = null;
        if (userInputsIndex > -1 && userInputsIndex < userInputs.size()) {
            output = userInputs.get(userInputsIndex);
        }

        if (userInputsIndex == userInputs.size()) {
            output = firstUpEnteredString;
        }

        return output;
    }

    /**
     * Increments the index that the user input history points to if a future user input exists.
     */
    private void incrementUserInputsIndex() {
        if (userInputsIndex + 1 <= userInputs.size() && userInputsIndex <= userInputs.size()) {
            userInputsIndex++;
        }
    }

    /**
     * Decrements the index that the user input history points to if a previous user input exists.
     */
    private void decrementUserInputsIndex() {
        if (!firstDecrementAfterUserInput && userInputsIndex > 0) {
            userInputsIndex--;
        }
        firstDecrementAfterUserInput = false;
    }

    /**
     * Gets the text in the command text field.
     * @return The texts in the command text field.
     */
    private String getTextInCommandTextField() {
        return commandTextField.getText();
    }

    /**
     * Moves the caret position to the end of the text.
     */
    private void positionCaretInTheEnd() {
        getCommandTextField().positionCaret(getTextInCommandTextField().length());
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
