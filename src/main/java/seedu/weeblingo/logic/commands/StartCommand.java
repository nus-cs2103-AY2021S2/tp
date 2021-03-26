package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;

/**
 * Starts the quiz.
 */
public class StartCommand extends Command {

    public static final String COMMAND_WORD = "start";

    public static final String MESSAGE_SUCCESS = "Here is the first question.\n"
            + "Enter \"end\" to end the quiz, \"check\" to check the answer, "
            + "and \"next\" to move to the next question.";

    private int numOfQnsForQuizSession;

    /**
     * Command to start a quiz session with no specified number of questions.
     */
    public StartCommand() {}

    /**
     * Command to start a quiz session with a specified number of questions.
     */
    public StartCommand(int n) {
        numOfQnsForQuizSession = n;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();
        if (currentMode == Mode.MODE_QUIZ) {
            model.setNumOfQnsForQuizSession(numOfQnsForQuizSession);
            model.startQuiz();
            model.getMode().switchModeQuizSession();
            return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_IN_QUIZ_MODE);
        }
    }
}

