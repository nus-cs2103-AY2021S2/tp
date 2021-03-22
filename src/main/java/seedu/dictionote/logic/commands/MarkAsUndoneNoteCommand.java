package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

public class MarkAsUndoneNoteCommand extends Command {
    public static final String COMMAND_WORD = "markasundonenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark a note as not done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_AS_DONE_NOTE_SUCCESS = "Mark as not done note: %1$s";

    private final Index targetIndex;

    public MarkAsUndoneNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Note> lastShownList = model.getFilteredNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToMarkAsUndone = lastShownList.get(targetIndex.getZeroBased());
        Note markAsUndoneNote = noteToMarkAsUndone.markAsUndoneNote(noteToMarkAsUndone.getNote(),
                noteToMarkAsUndone.getTags(), noteToMarkAsUndone.getCreateTime());

        model.setNote(noteToMarkAsUndone, markAsUndoneNote);
        return new CommandResult(String.format(MESSAGE_MARK_AS_DONE_NOTE_SUCCESS, markAsUndoneNote));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAsUndoneNoteCommand // instanceof handles nulls
                && targetIndex.equals(((MarkAsUndoneNoteCommand) other).targetIndex)); // state check
    }
}
