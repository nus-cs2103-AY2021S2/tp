package seedu.dictionote.logic.commands;

import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.commands.EditNoteCommand.MESSAGE_DUPLICATE_NOTE;
import static seedu.dictionote.logic.commands.EditNoteCommand.MESSAGE_NOTHING_CHANGE_NOTE;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

/**
 * Edit the note in edit mode.
 */
public class EditModeSaveCommand extends Command {
    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_EDIT_MODE_EXIT_SUCCESS = "Save and quit edit mode.";
    public static final String MESSAGE_NOT_IN_EDIT_MODE = "Not in edit mode.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.onEditModeNote()) {
            throw new CommandException(MESSAGE_NOT_IN_EDIT_MODE);
        }
        Note noteToEdit = model.getNoteShown();

        if (model.getEditedNoteShownContent().equals(noteToEdit.getNote())) {
            throw new CommandException(MESSAGE_NOTHING_CHANGE_NOTE);
        }

        Note editedNote = noteToEdit.createEditedNote(model.getEditedNoteShownContent(), noteToEdit.getTags(),
            noteToEdit.getCreateTime(), now(), noteToEdit.isDone());


        if (model.hasNote(editedNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.setNote(noteToEdit, editedNote);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.showNote(editedNote);
        return new CommandResult(MESSAGE_EDIT_MODE_EXIT_SUCCESS,
                UiAction.EDITMODEEXIT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof EditModeSaveCommand; // instanceof handles nulls
    }

}
