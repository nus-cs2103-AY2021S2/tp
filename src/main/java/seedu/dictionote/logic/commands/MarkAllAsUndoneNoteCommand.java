package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_NOTES;

import java.util.List;

import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

public class MarkAllAsUndoneNoteCommand extends Command {
    public static final String COMMAND_WORD = "markallasundonenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark all note as not done.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_MARK_ALL_AS_UNDONE_NOTE_SUCCESS = "Mark all notes as not done.";

    public MarkAllAsUndoneNoteCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        List<Note> lastShownList = model.getFilteredNoteList();

        for (int i = 0; i < lastShownList.size(); i++) {
            Note noteToMarkAsUndone = lastShownList.get(i);
            Note markAsUndoneNote = noteToMarkAsUndone.markAsUndoneNote(noteToMarkAsUndone.getNote(),
                    noteToMarkAsUndone.getTags(), noteToMarkAsUndone.getCreateTime());
            model.setNote(noteToMarkAsUndone, markAsUndoneNote);
        }
        return new CommandResult(String.format(MESSAGE_MARK_ALL_AS_UNDONE_NOTE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAllAsUndoneNoteCommand); // instanceof handles nulls
    }
}
