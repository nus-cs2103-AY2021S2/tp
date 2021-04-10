package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.tag.Tag;

/**
 * Enters quiz mode.
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "You are now in quiz mode. "
            + "You can start a quiz session.\n"
            + "Enter \"end\" to return to main menu.\n";

    public static final String MESSAGE_IN_QUIZ_SESSION = "You are in a quiz session! \n"
            + "Enter \"end\" to return to menu first or complete current quiz session "
            + "before entering quiz view.";

    public static final String MESSAGE_NO_TAGS = "All flashcards are shown.";

    public static final String MESSAGE_HAVE_TAGS = "Flashcards with the following tag(s) are shown: ";

    private Set<Tag> tags;

    public QuizCommand(Set<Tag> tagsSet) {
        this.tags = tagsSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int currentMode = model.getCurrentMode();

        if (currentMode == Mode.MODE_QUIZ_SESSION || currentMode == Mode.MODE_CHECK_SUCCESS) {
            throw new CommandException(MESSAGE_IN_QUIZ_SESSION);
        }

        if (currentMode == Mode.MODE_HISTORY || currentMode == Mode.MODE_LEARN) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_MENU_MODE);
        }

        assert currentMode == Mode.MODE_MENU || currentMode == Mode.MODE_QUIZ_SESSION_ENDED
                || currentMode == Mode.MODE_QUIZ;

        model.updateFilteredFlashcardList(flashcard -> flashcard.checkHasTags(tags));
        model.switchModeQuiz();
        if (tags.isEmpty()) {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_NO_TAGS, false, false);
        } else {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_HAVE_TAGS + tags.toString(), false, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof QuizCommand) {
            QuizCommand otherCommand = (QuizCommand) other;
            return this.tags.containsAll(otherCommand.tags) && otherCommand.tags.containsAll(this.tags);
        } else {
            return false;
        }
    }

}
