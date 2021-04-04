package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.tag.Tag;


/**
 * Starts the quiz.
 */
public class StartCommand extends Command {

    public static final String COMMAND_WORD = "start";

    public static final String MESSAGE_SUCCESS = "Here is the first question.\n"
            + "Enter \"end\" to end the quiz, \"check\" to check the answer, "
            + "and \"next\" to move to the next question.";

    public static final String MESSAGE_TAG_NOT_FOUND = "Oops, no flashcards have that tag!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": starts a new quiz with the specified number of "
            + "questions, filtered to have only questions that have the specified tag(s). All parameters are"
            + "optional. \n"
            + "Parameters: QUIZ_SIZE, TAGS\n"
            + "Example: " + COMMAND_WORD + " n/5 t/hiragana t/gojuon";

    public static final String MESSAGE_INVALID_NUMBER_OF_QUESTIONS = "Oops! Number of questions must "
            + "be a positive integer!";

    public static final String MESSAGE_NUMBER_TOO_LARGE = "The number you specified is too large!\n"
            + "Please enter a number that is smaller than or equals to 1000000.";

    public static final String MESSAGE_NUMBER_LARGER_THAN_DATABASE_FLASHCARDS_SIZE =
            "The number you specified is larger than the "
                    + "number of flashcards in the database.\n"
                    + "Please enter a number that is smaller than or equals to ";

    private int numOfQnsForQuizSession;

    private Set<Tag> tags;

    /**
     * Command to start a quiz session of length specified by numberOfQuestions
     * and filtered by a specified set of Tags.
     * @param numberOfQuestions The specified length of the quiz.
     * @param tagsSet The specified tags by which to filter the questions.
     */
    public StartCommand(int numberOfQuestions, Set<Tag> tagsSet) {
        this.numOfQnsForQuizSession = numberOfQuestions;
        this.tags = tagsSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();
        if (currentMode == Mode.MODE_QUIZ) {
            model.startQuiz(numOfQnsForQuizSession, tags);
            model.switchModeQuizSession();
            return new CommandResult(MESSAGE_SUCCESS, false, false);
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_IN_QUIZ_MODE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof StartCommand) {
            StartCommand otherCommand = (StartCommand) other;
            return this.numOfQnsForQuizSession == otherCommand.numOfQnsForQuizSession
                && this.tags.containsAll(otherCommand.tags)
                && otherCommand.tags.containsAll(this.tags);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return numOfQnsForQuizSession + tags.toString();
    }
}
