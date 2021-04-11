package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.dictionote.logic.commands.EditModeSaveCommand.MESSAGE_EDIT_MODE_EXIT_SUCCESS;
import static seedu.dictionote.logic.commands.EditNoteCommand.MESSAGE_DUPLICATE_NOTE;
import static seedu.dictionote.logic.commands.EditNoteCommand.MESSAGE_NOTHING_CHANGE_NOTE;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.EditNoteDescriptorBuilder;
import seedu.dictionote.testutil.TypicalNoteContentConfig;
import seedu.dictionote.ui.NoteContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditModeSaveCommandTest {

    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
        getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalContactsList(), new UserPrefs(),
        getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());


    @Test
    public void execute_saveEditMode_failure() {
        //Note in edit mode fail
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
        assertCommandFailure(new EditModeSaveCommand(), model, EditModeSaveCommand.MESSAGE_NOT_IN_EDIT_MODE);

        //Note not change fail
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditModeNoteSame());
        model.showNote(model.getNoteBook().getNoteList().get(INDEX_SECOND_NOTE.getZeroBased()));
        assertCommandFailure(new EditModeSaveCommand(), model, MESSAGE_NOTHING_CHANGE_NOTE);

        //Note is duplicate fail
        model.setNoteContentConfig(TypicalNoteContentConfig.getNoteContentConfigStubEditModeNoteEditedDuplicate());
        model.showNote(model.getNoteBook().getNoteList().get(INDEX_SECOND_NOTE.getZeroBased()));
        assertCommandFailure(new EditModeSaveCommand(), model, MESSAGE_DUPLICATE_NOTE);

    }

    @Test
    public void execute_saveEditMode_success() {

        //Note change
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        model.showNote(model.getNoteBook().getNoteList().get(INDEX_SECOND_NOTE.getZeroBased()));
        assertCommandSuccess(new EditModeSaveCommand(),model, MESSAGE_EDIT_MODE_EXIT_SUCCESS, expectedModel);

    }

    @Test
    public void execute_noteNoteEdited_failure() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);


        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());

        // edit contact in filtered list into a duplicate in contacts list.
        Note noteInList = model.getNoteBook().getNoteList().get(INDEX_SECOND_NOTE.getZeroBased());
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE,
            new EditNoteDescriptorBuilder(noteInList).build());

        assertCommandFailure(editNoteCommand, model, EditNoteCommand.MESSAGE_NOTHING_CHANGE_NOTE);
    }

    @Test
    public void equal() {
        EditModeSaveCommand editModeSaveCommand1 = new EditModeSaveCommand();
        EditModeSaveCommand editModeSaveCommand2 = new EditModeSaveCommand();

        // same object -> returns true
        assertTrue(editModeSaveCommand1.equals(editModeSaveCommand1));

        // same values -> returns true
        assertTrue(editModeSaveCommand1.equals(editModeSaveCommand2));

        // different types -> returns false
        assertFalse(editModeSaveCommand1.equals(1));

        // null -> returns false
        assertFalse(editModeSaveCommand1.equals(null));
    }

}
