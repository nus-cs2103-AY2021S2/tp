package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.tag.Tag;

import java.util.Set;

/**
 * Enter flashcard quiz mode.
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "You are now in quiz mode.\n"
            + "Enter \"start\" to start quiz.";

    private Set<Tag> tags;

    public QuizCommand(Set<Tag> tagsSet) {
        this.tags = tagsSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();
        if (currentMode == Mode.MODE_MENU) {
            model.updateFilteredFlashcardList(flashcard -> flashcard.checkHasTags(tags));
            model.switchModeQuiz();
            return new CommandResult(MESSAGE_SUCCESS, false, false);
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_IN_MENU_MODE);
        }
    }
}
