package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

public class MarkAsDoneNoteCommand extends Command {
    public static final String COMMAND_WORD = "markasdonenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark a note as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_AS_DONE_NOTE_SUCCESS = "Mark as done note: %1$s";

    private final Index targetIndex;

    public MarkAsDoneNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        List<Note> lastShownList = model.getFilteredNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToMarkAsDone = lastShownList.get(targetIndex.getZeroBased());
        Note markAsDoneNote = noteToMarkAsDone.markAsDoneNote(noteToMarkAsDone.getNote(),
                noteToMarkAsDone.getTags(), noteToMarkAsDone.getCreateTime());

        model.setNote(noteToMarkAsDone, markAsDoneNote);
        return new CommandResult(String.format(MESSAGE_MARK_AS_DONE_NOTE_SUCCESS, markAsDoneNote));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAsDoneNoteCommand // instanceof handles nulls
                && targetIndex.equals(((MarkAsDoneNoteCommand) other).targetIndex)); // state check
    }
}
