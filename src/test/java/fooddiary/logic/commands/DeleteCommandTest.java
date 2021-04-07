package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandFailure;
import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.logic.commands.CommandTestUtil.showEntriesAtIndexes;
import static fooddiary.logic.commands.CommandTestUtil.showEntryAtIndex;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithMultipleEntries;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithSingleEntry;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.Entry;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model modelMultipleEntries = new ModelManager(getTypicalFoodDiaryWithMultipleEntries(), new UserPrefs());
    private Model modelSingleEntry = new ModelManager(getTypicalFoodDiaryWithSingleEntry(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Entry entryToDelete = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete);

        ModelManager expectedModel = new ModelManager(modelMultipleEntries.getFoodDiary(), new UserPrefs());
        expectedModel.deleteEntry(entryToDelete);

        assertCommandSuccess(deleteCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelMultipleEntries.getFilteredEntryList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        //Check when model has multiple entries (to check for plural message)
        assertCommandFailure(deleteCommand, modelMultipleEntries, String.format(
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));

        //Check when model has single entry (to check for singular message)
        assertCommandFailure(deleteCommand, modelSingleEntry,
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showEntryAtIndex(modelMultipleEntries, INDEX_FIRST_ENTRY);

        Entry entryToDelete = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete);

        Model expectedModel = new ModelManager(modelMultipleEntries.getFoodDiary(), new UserPrefs());
        expectedModel.deleteEntry(entryToDelete);
        showNoEntry(expectedModel);

        assertCommandSuccess(deleteCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEntriesAtIndexes(modelMultipleEntries, INDEX_FIRST_ENTRY, INDEX_SECOND_ENTRY);

        Index outOfBoundIndex = INDEX_THIRD_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < modelMultipleEntries.getFoodDiary().getEntryList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        //Check when model has multiple entries (to check for plural message)
        assertCommandFailure(deleteCommand, modelMultipleEntries,
                String.format(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));


        showEntryAtIndex(modelMultipleEntries, INDEX_FIRST_ENTRY);

        outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < modelMultipleEntries.getFoodDiary().getEntryList().size());

        deleteCommand = new DeleteCommand(outOfBoundIndex);

        //Check when model has single entry (to check for singular message)
        assertCommandFailure(deleteCommand, modelMultipleEntries,
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ENTRY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ENTRY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ENTRY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different entry -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEntry(Model model) {
        model.updateFilteredEntryList(p -> false);

        assertTrue(model.getFilteredEntryList().isEmpty());
    }
}
