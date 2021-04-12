package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_NOTES;

import java.util.List;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

/**
 * Marks every note as undone.
 */
public class MarkAllAsUndoneNoteCommand extends Command {
    public static final String COMMAND_WORD = "markallasundonenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark all note as not done.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_MARK_ALL_AS_UNDONE_NOTE_SUCCESS = "Mark all notes as not done.";

    /**
     * Constructor for MarkAllAsUndoneNoteCommand.
     */
    public MarkAllAsUndoneNoteCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        List<Note> lastShownList = model.getFilteredNoteList();

        for (int i = 0; i < lastShownList.size(); i++) {
            Note noteToMarkAsUndone = lastShownList.get(i);
            Note markAsUndoneNote = noteToMarkAsUndone.markAsUndoneNote(noteToMarkAsUndone.getNote(),
                    noteToMarkAsUndone.getTags(), noteToMarkAsUndone.getCreateTime());
            model.setNote(noteToMarkAsUndone, markAsUndoneNote);
        }
        return new CommandResult(String.format(MESSAGE_MARK_ALL_AS_UNDONE_NOTE_SUCCESS),
            UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAllAsUndoneNoteCommand); // instanceof handles nulls
    }
}
