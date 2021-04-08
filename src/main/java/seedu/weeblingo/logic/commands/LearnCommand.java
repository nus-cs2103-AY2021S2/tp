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
            + "You can add or delete tags for the flashcards.\n"
            + "Enter \"end\" to return to menu.\n";

    public static final String MESSAGE_NO_TAGS = "All flashcards are shown.";

    public static final String MESSAGE_HAVE_TAGS = "Flashcards with the following tag(s) are shown: ";

    private Set<Tag> tags;

    public LearnCommand(Set<Tag> tagsSet) {
        this.tags = tagsSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int currentMode = model.getCurrentMode();

        if (currentMode != Mode.MODE_MENU && currentMode != Mode.MODE_LEARN) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_MENU_MODE);
        }

        model.updateFilteredFlashcardList(flashcard -> flashcard.checkHasTags(tags));
        model.switchModeLearn();

        if (tags.isEmpty()) {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_NO_TAGS, false, false);
        } else {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_HAVE_TAGS + tags.toString(), false, false);
        }
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
