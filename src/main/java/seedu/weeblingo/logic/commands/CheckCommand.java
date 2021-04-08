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

    public static final String CORRECT_ATTEMPT = "You answered this question correctly!\n";

    public static final String WRONG_ATTEMPT = " is incorrect.\n";

    public static final String MESSAGE_HELPER = "Enter \"end\" to return to menu "
            + "or \"next\" to move to the next question.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": checks user answer for displayed flashcard.\n"
            + "Parameters: ATTEMPT\n"
            + "Example: " + COMMAND_WORD + " apple";

    public static final String MULTIPLE_CHECKING_AFTER_SUCCESS = "You already got this question correct.\n"
            + "Please enter \"next\" to go to the next question "
            + "or \"end\" to return to menu. \n";

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

        int currentMode = model.getCurrentMode();

        if (currentMode == Mode.MODE_CHECK_SUCCESS) {
            throw new CommandException(MULTIPLE_CHECKING_AFTER_SUCCESS);
        }

        if (currentMode != Mode.MODE_QUIZ_SESSION) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_QUIZ_SESSION);
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
