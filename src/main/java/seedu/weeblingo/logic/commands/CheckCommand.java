package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;

/**
 * Reveals answer for current quiz question
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";
    public static final String CORRECT_ATTEMPT = "You answered correctly!\n";
    public static final String WRONG_ATTEMPT = " is incorrect.\n";
    public static final String MESSAGE_SUCCESS = "Enter \"end\" to end the quiz "
            + "and \"next\" to move to the next question.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": checks user answer for displayed flashcard.\n"
            + "Parameters: ATTEMPT\n"
            + "Example: " + COMMAND_WORD + " apple";

    private final String attempt;

    /**
     * Creates a CheckCommand to check the specified attempt
     * @param attempt
     */
    public CheckCommand(String attempt) {
        requireNonNull(attempt);
        this.attempt = attempt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getCurrentFlashcard();
        } catch (NullPointerException e) {
            throw new CommandException(Messages.NO_QUIZ_ERROR_MESSAGE);
        }
        if (model.isCorrectAttempt(attempt)) {
            model.switchModeCheckSuccess();
            return new CommandResult(CORRECT_ATTEMPT + MESSAGE_SUCCESS, false, false);
        } else {
            return new CommandResult(attempt + WRONG_ATTEMPT + MESSAGE_SUCCESS, false, false);
        }

    }
}
