package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.dictionote.logic.commands.CommandTestUtil.showNoteAtTwoIndexes;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_THIRD_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
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

public class MergeNoteCommandTest {
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
        assertCommandFailure(new MergeNoteCommand(INDEX_FIRST_NOTE, INDEX_SECOND_NOTE), editModeModel,
                Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Note firstNoteToMerge = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        Note secondNoteToMerge = model.getFilteredNoteList().get(INDEX_SECOND_NOTE.getZeroBased());
        MergeNoteCommand mergeNoteCommand = new MergeNoteCommand(INDEX_FIRST_NOTE, INDEX_SECOND_NOTE);

        String expectedMessage = String.format(MergeNoteCommand.MESSAGE_MERGE_NOTE_SUCCESS,
                firstNoteToMerge, secondNoteToMerge);

        ModelManager expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.mergeNote(firstNoteToMerge, secondNoteToMerge);

        assertCommandSuccess(mergeNoteCommand, model, expectedMessage,
            UiAction.OPEN, UiActionOption.NOTE_LIST, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index firstOutOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        Index secondOutOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 2);
        MergeNoteCommand mergeNoteCommand = new MergeNoteCommand(firstOutOfBoundIndex,
                secondOutOfBoundIndex);

        assertCommandFailure(mergeNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showNoteAtTwoIndexes(model, INDEX_FIRST_NOTE, INDEX_SECOND_NOTE);

        Note firstNoteToMerge = model.getFilteredNoteList().get(INDEX_FIRST_NOTE.getZeroBased());
        Note secondNoteToMerge = model.getFilteredNoteList().get(INDEX_SECOND_NOTE.getZeroBased());
        MergeNoteCommand mergeNoteCommand = new MergeNoteCommand(INDEX_FIRST_NOTE, INDEX_SECOND_NOTE);

        String expectedMessage = String.format(MergeNoteCommand.MESSAGE_MERGE_NOTE_SUCCESS,
                firstNoteToMerge, secondNoteToMerge);

        Model expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        showNoteAtTwoIndexes(expectedModel, INDEX_FIRST_NOTE, INDEX_SECOND_NOTE);
        expectedModel.mergeNote(firstNoteToMerge, secondNoteToMerge);

        showNoteAtIndex(expectedModel, INDEX_FIRST_NOTE);

        assertCommandSuccess(mergeNoteCommand, model, expectedMessage,
            UiAction.OPEN, UiActionOption.NOTE_LIST, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        Index outOfBoundIndex = INDEX_SECOND_NOTE;
        Index anotherOutOfBoundIndex = INDEX_THIRD_NOTE;

        // ensures that outOfBoundIndex is still in bounds of dictionote book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNoteBook().getNoteList().size());

        MergeNoteCommand mergeNoteCommand = new MergeNoteCommand(outOfBoundIndex, anotherOutOfBoundIndex);

        assertCommandFailure(mergeNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MergeNoteCommand mergeFirstCommand = new MergeNoteCommand(INDEX_FIRST_NOTE, INDEX_SECOND_NOTE);
        MergeNoteCommand mergeSecondCommand = new MergeNoteCommand(INDEX_SECOND_NOTE, INDEX_THIRD_NOTE);

        // same object -> returns true
        assertTrue(mergeFirstCommand.equals(mergeFirstCommand));

        // same values -> returns true
        MergeNoteCommand mergeFirstCommandCopy = new MergeNoteCommand(INDEX_FIRST_NOTE, INDEX_SECOND_NOTE);
        assertTrue(mergeFirstCommand.equals(mergeFirstCommandCopy));

        // different types -> returns false
        assertFalse(mergeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(mergeFirstCommand.equals(null));

        // different note -> returns false
        assertFalse(mergeFirstCommand.equals(mergeSecondCommand));
    }
}
