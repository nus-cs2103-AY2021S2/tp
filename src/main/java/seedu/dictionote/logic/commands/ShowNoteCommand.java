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

/**
 * Show a specific note in the notes list to the user.
 */
public class ShowNoteCommand extends Command {
    public static final String COMMAND_WORD = "shownote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Showed a specific note identified by the index number used in the displayed note list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_NOTE_SUCCESS = "Here is the note";

    private final Index targetIndex;

    public ShowNoteCommand(Index targetIndex) {
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

        Note noteToShow = lastShownList.get(targetIndex.getZeroBased());
        model.showNote(noteToShow);
        return new CommandResult(String.format(MESSAGE_SHOW_NOTE_SUCCESS, noteToShow),
                UiAction.OPEN, UiActionOption.NOTE_CONTENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowNoteCommand // instanceof handles nulls
                && targetIndex.equals(((ShowNoteCommand) other).targetIndex)); // state check
    }
}
