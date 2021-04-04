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

    public static final String MESSAGE_SUCCESS = "You are now in learn mode.\n"
            + "Enter \"end\" to end your study or "
            + "\"quiz\" to start a quiz session on the flashcards.";

    private Set<Tag> tags;

    public LearnCommand(Set<Tag> tagsSet) {
        this.tags = tagsSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentMode = model.getCurrentMode();
        if (currentMode == Mode.MODE_MENU) {
            if (tags.isEmpty()) {
                model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);
            } else {
                model.updateFilteredFlashcardList(flashcard -> flashcard.checkHasTags(tags));
            }
            model.switchModeLearn();
            return new CommandResult(MESSAGE_SUCCESS, false, false);
        } else {
            throw new CommandException(Messages.MESSAGE_NOT_IN_MENU_MODE);
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
