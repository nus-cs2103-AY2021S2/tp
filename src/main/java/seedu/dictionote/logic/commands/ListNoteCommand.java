package seedu.dictionote.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_NOTES;

import seedu.dictionote.model.Model;

/**
 * Lists all notes in the notes list to the user.
 */
public class ListNoteCommand extends Command {
    public static final String COMMAND_WORD = "listnote";

    public static final String MESSAGE_SUCCESS = "Listed all notes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
