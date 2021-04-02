package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;

/**
 * Gets the next flashcard.
 */
public class NextCommand extends Command {

    public static final String COMMAND_WORD = "next";

    public static final String MESSAGE_SUCCESS = "Here is the next question.\n"
            + "Enter \"end\" to end the quiz, \"check\" to check the answer, "
            + "and \"next\" to move to the next question.";

    public static final String MESSAGE_SCORE_ADDED = "Your score has been recorded:\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();
        if (model.getQuizInstance() == null) {
            throw new CommandException(Messages.NO_QUIZ_ERROR_MESSAGE);
        }

        if (model.getNextFlashcard() == null) {
            if (currentMode == Mode.MODE_QUIZ_SESSION_ENDED) {
                throw new CommandException(Messages.MESSAGE_QUIZ_ALREADY_ENDED);
            }
            String quizStatistics = model.getQuizStatisticString();
            model.addScore();
            model.switchModeQuizSessionEnded();
            return new CommandResult(Messages.MESSAGE_QUIZ_ENDED + MESSAGE_SCORE_ADDED + quizStatistics);
        }

        model.switchModeQuizSession();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
