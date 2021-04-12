package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

//Todo
public class MergeNoteCommand extends Command {
    public static final String COMMAND_WORD = "mergenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Merges the two note identified by the two indexes used in the displayed note list.\n"
            + "Parameters: "
            + "INDEX1 (must be a positive integer) "
            + "INDEX2 (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2";

    public static final String MESSAGE_MERGE_NOTE_SUCCESS = "Merged note: %1$s and %2$s";

    private final Index firstIndex;
    private final Index secondIndex;

    /**
     * Merges two notes identified using it's displayed index from the notes list.
     */
    public MergeNoteCommand(Index firstIndex, Index secondIndex) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        List<Note> lastShownList = model.getFilteredNoteList();

        if (firstIndex.getZeroBased() >= lastShownList.size()
                || secondIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note firstNote = lastShownList.get(firstIndex.getZeroBased());
        Note secondNote = lastShownList.get(secondIndex.getZeroBased());
        model.mergeNote(firstNote, secondNote);

        return new CommandResult(String.format(MESSAGE_MERGE_NOTE_SUCCESS, firstNote, secondNote),
                UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MergeNoteCommand // instanceof handles nulls
                && firstIndex.equals(((MergeNoteCommand) other).firstIndex)
                && secondIndex.equals(((MergeNoteCommand) other).secondIndex)); // state check
    }
}
