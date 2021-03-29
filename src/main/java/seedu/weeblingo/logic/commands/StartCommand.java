package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.tag.Tag;

import java.util.Set;

/**
 * Starts the quiz.
 */
public class StartCommand extends Command {

    public static final String COMMAND_WORD = "start";

    public static final String MESSAGE_SUCCESS = "Here is the first question.\n"
            + "Enter \"end\" to end the quiz, \"check\" to check the answer, "
            + "and \"next\" to move to the next question.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": starts a new quiz with the specified number of "
            + "questions or a new quiz with only questions that have the specified tag(s).\n"
            + "Parameters: QUIZ_SIZE\n"
            + "Example: " + COMMAND_WORD + " 5\n"
            + "or\n"
            + "Parameters: TAGS\n"
            + "Example: " + COMMAND_WORD + " hiragana gojuon";

    private int numOfQnsForQuizSession;

    private Set<Tag> tags;

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

    public StartCommand(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();
        if (currentMode == Mode.MODE_QUIZ) {
            model.setNumOfQnsForQuizSession(numOfQnsForQuizSession);
            model.setTagsForQuizSession(tags);
            model.startQuiz();
            model.switchModeQuizSession();
            return new CommandResult(MESSAGE_SUCCESS, false, false);
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_IN_QUIZ_MODE);
        }
    }
}

