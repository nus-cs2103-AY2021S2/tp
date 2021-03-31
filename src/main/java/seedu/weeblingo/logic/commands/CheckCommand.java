package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Answer;

/**
 * Reveals answer for current quiz question
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";
    public static final String CORRECT_ATTEMPT = "You answered correctly!\n";
    public static final String WRONG_ATTEMPT = " is incorrect.\n";
    public static final String MESSAGE_HELPER = "Enter \"end\" to end the quiz "
            + "and \"next\" to move to the next question.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": checks user answer for displayed flashcard.\n"
            + "Parameters: ATTEMPT\n"
            + "Example: " + COMMAND_WORD + " apple";

    private final Answer attempt;

    /**
     * Creates a CheckCommand to check the specified attempt
     * @param attempt
     */
    public CheckCommand(Answer attempt) {
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
        if (model.getCurrentMode() == Mode.MODE_CHECK_SUCCESS) {
            throw new CommandException(Messages.MULTIPLE_CHECKING_AFTER_SUCCESS);
        }
        // Model::isCorrectAttempt() modifies the quiz statistic, FYI
        if (model.isCorrectAttempt(attempt)) {
            model.switchModeCheckSuccess();
            String quizStatistics = model.getQuizStatisticString() + "\n";
            return new CommandResult(CORRECT_ATTEMPT + quizStatistics + MESSAGE_HELPER,
                    false, false);
        } else {
            String quizStatistics = model.getQuizStatisticString() + "\n";
            return new CommandResult(attempt + WRONG_ATTEMPT + quizStatistics + MESSAGE_HELPER,
                    false, false);
        }

    }
}
