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

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowNoteCommand}.
 */
public class ShowNoteCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());

        assertCommandFailure(new ShowNoteCommand(INDEX_FIRST_NOTE), editModeModel,
            Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());

        Note noteToShow = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        ShowNoteCommand showNoteCommand = new ShowNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(ShowNoteCommand.MESSAGE_SHOW_NOTE_SUCCESS, noteToShow);

        ModelManager expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        expectedModel.showNote(noteToShow);

        assertCommandSuccess(showNoteCommand, model, expectedMessage,
            UiAction.OPEN, UiActionOption.NOTE_CONTENT, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        ShowNoteCommand showNoteCommand = new ShowNoteCommand(outOfBoundIndex);

        assertCommandFailure(showNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());

        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Note noteToShow = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        ShowNoteCommand showNoteCommand = new ShowNoteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(ShowNoteCommand.MESSAGE_SHOW_NOTE_SUCCESS, noteToShow);

        Model expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        expectedModel.showNote(noteToShow);

        assertCommandSuccess(showNoteCommand, model, expectedMessage,
            UiAction.OPEN, UiActionOption.NOTE_CONTENT, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());

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
}
