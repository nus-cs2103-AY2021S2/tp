package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    public static final String DUPLICATE_NAME_AND_LESSON = "The student name ";
    public static final String DUPLICATE_NAME = "This student name ";
    public static final String DUPLICATE_LESSON = "You have a lesson at ";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private String previousUserInput;
    private boolean isWaitForNextInput;

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
        previousUserInput = "";
        isWaitForNextInput = false;
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
            if (e.getMessage().startsWith(DUPLICATE_NAME_AND_LESSON)
                    || e.getMessage().startsWith(DUPLICATE_NAME)
                    || e.getMessage().startsWith(DUPLICATE_LESSON)) {
                previousUserInput = commandText;
                commandTextField.setText("");
            }
        }
    }

    public String getPreviousUserInput() {
        return this.previousUserInput;
    }

    public boolean isWaitForNextInput() {
        return this.isWaitForNextInput;
    }

    public void setWaitForNextInput(boolean isWaitForNextInput) {
        this.isWaitForNextInput = isWaitForNextInput;
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
