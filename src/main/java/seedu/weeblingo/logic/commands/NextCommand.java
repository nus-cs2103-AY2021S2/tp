package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;

/**
 * Gets the next flashcard.
 */
public class NextCommand extends Command {

    public static final String COMMAND_WORD = "next";

    public static final String MESSAGE_SUCCESS = "Here is the next question.\n"
            + "Enter \"end\" to end the quiz, \"check\" to check the answer, "
            + "and \"next\" to move to the next question.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getQuizInstance() == null) {
            throw new CommandException(Messages.NO_QUIZ_ERROR_MESSAGE);
        }

        if (model.getNextFlashcard() == null) {
            String quizSessionTime = model.getQuizInstance().getQuizSessionDuration();
            String endOfQuizSessionMessage = "Your quiz session duration is " + quizSessionTime;
            return new CommandResult(Messages.QUIZ_END_MESSAGE + endOfQuizSessionMessage);
        }

        model.switchModeQuizSession();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
