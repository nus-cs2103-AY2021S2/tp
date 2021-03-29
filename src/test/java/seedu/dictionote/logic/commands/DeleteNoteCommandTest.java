package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteNoteCommand}.
 */
public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());


    @BeforeEach
    public void init() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
    }

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        assertCommandFailure(new DeleteNoteCommand(INDEX_FIRST_NOTE), editModeModel,
            Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Note noteToDelete = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete);

        ModelManager expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.deleteNote(noteToDelete);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Note noteToDelete = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS,
                noteToDelete);

        Model expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.deleteNote(noteToDelete);
        showNoNote(expectedModel);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index outOfBoundIndex = INDEX_SECOND_NOTE;
        // ensures that outOfBoundIndex is still in bounds of dictionote book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNoteBook().getNoteList().size());

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteNoteCommand deleteFirstCommand = new DeleteNoteCommand(INDEX_FIRST_NOTE);
        DeleteNoteCommand deleteSecondCommand = new DeleteNoteCommand(INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteNoteCommand deleteFirstCommandCopy = new DeleteNoteCommand(INDEX_FIRST_NOTE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different note -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no note.
     */
    private void showNoNote(Model model) {
        model.updateFilteredNoteList(p -> false);

        assertTrue(model.getFilteredNoteList().isEmpty());
    }
}
