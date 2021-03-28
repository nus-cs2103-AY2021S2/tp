package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_NOTES;

import seedu.dictionote.model.Model;

/**
 * Sort notes in the note list.
 */
public class SortNoteByTimeCommand extends Command {
    public static final String COMMAND_WORD = "sortnotebytime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all note the note list chronologically.\n"
            + "Parameters: sortnotebytime\n";

    public static final String MESSAGE_SORT_NOTE_SUCCESS = "Note sorted";

    /**
     * Sort notes in the note list.
     */
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortNoteByTime();
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(MESSAGE_SORT_NOTE_SUCCESS);
    }

}
