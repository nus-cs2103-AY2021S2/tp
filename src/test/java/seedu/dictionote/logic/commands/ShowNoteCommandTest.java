package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowNoteCommand}.
 */
public class ShowNoteCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    //Need to change test case
    /*
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Note noteToShow = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        ShowNoteCommand showNoteCommand = new ShowNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(ShowNoteCommand.MESSAGE_SHOW_NOTE_SUCCESS, noteToShow);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.showNote(noteToShow);

        assertCommandSuccess(showNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        ShowNoteCommand showNoteCommand = new ShowNoteCommand(outOfBoundIndex);

        assertCommandFailure(showNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Note noteToShow = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        ShowNoteCommand showNoteCommand = new ShowNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(ShowNoteCommand.MESSAGE_SHOW_NOTE_SUCCESS, noteToShow);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.showNote(noteToShow);
        showNoNote(expectedModel);

        assertCommandSuccess(showNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index outOfBoundIndex = INDEX_SECOND_NOTE;
        // ensures that outOfBoundIndex is still in bounds of dictionote book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNoteBook().getNoteList().size());

        ShowNoteCommand showNoteCommand = new ShowNoteCommand(outOfBoundIndex);

        assertCommandFailure(showNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowNoteCommand showFirstCommand = new ShowNoteCommand(INDEX_FIRST_NOTE);
        ShowNoteCommand showSecondCommand = new ShowNoteCommand(INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowNoteCommand showFirstCommandCopy = new ShowNoteCommand(INDEX_FIRST_NOTE);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different note -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
    */
    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoNote(Model model) {
        model.updateFilteredNoteList(p -> false);

        assertTrue(model.getFilteredNoteList().isEmpty());
    }
}
