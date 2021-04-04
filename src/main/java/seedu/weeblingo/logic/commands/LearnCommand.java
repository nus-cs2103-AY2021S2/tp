package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.weeblingo.commons.core.Messages;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.tag.Tag;

/**
 * Lists all flashcards in the address book to the user.
 */
public class LearnCommand extends Command {

    public static final String COMMAND_WORD = "learn";

    public static final String MESSAGE_SUCCESS = "You are now in learn mode. "
            + "You can now add or delete tags for the flashcards.\n"
            + "Enter \"end\" to return to menu mode.";

    public static final String MESSAGE_ALREADY_IN_LEARN_MODE = "You are already in learn mode.";

    private Set<Tag> tags;

    public LearnCommand(Set<Tag> tagsSet) {
        this.tags = tagsSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int currentMode = model.getCurrentMode();
        if (currentMode == Mode.MODE_LEARN) {
            throw new CommandException(MESSAGE_ALREADY_IN_LEARN_MODE);
        }

        if (currentMode != Mode.MODE_MENU) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_MENU_MODE);
        }

        if (tags.isEmpty()) {
            model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);
        } else {
            model.updateFilteredFlashcardList(flashcard -> flashcard.checkHasTags(tags));
        }

        model.switchModeLearn();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof LearnCommand) {
            LearnCommand otherCommand = (LearnCommand) other;
            return this.tags.containsAll(otherCommand.tags) && otherCommand.tags.containsAll(this.tags);
        } else {
            return false;
        }
    }
}
