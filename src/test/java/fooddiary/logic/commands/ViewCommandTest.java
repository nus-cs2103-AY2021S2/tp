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
 * {@code ViewCommand}.
 */
class ViewCommandTest {

    private Model modelMultipleEntries = new ModelManager(getTypicalFoodDiaryWithMultipleEntries(), new UserPrefs());
    private Model modelSingleEntry = new ModelManager(getTypicalFoodDiaryWithSingleEntry(), new UserPrefs());

    /**
     * Checks if given valid index number for unfiltered entry list, correct entry is returned
     */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Entry entryToView = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_ENTRY_SUCCESS, entryToView);

        ModelManager expectedModel = new ModelManager(modelMultipleEntries.getFoodDiary(), new UserPrefs());

        assertCommandSuccess(viewCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    /**
     * Checks if given invalid index number for unfiltered entry list throws CommandException
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelMultipleEntries.getFilteredEntryList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        //Check when model has multiple entries (to check for plural message)
        assertCommandFailure(viewCommand, modelMultipleEntries, String.format(
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));

        //Check when model has multiple entries (to check for singular message)
        assertCommandFailure(viewCommand, modelSingleEntry,
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
    }

    /**
     * Checks if given valid index number for filtered entry list, correct entry is returned
     */
    @Test
    public void execute_validIndexFilteredList_success() {
        showEntryAtIndex(modelMultipleEntries, INDEX_FIRST_ENTRY);

        Entry entryToView = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_ENTRY_SUCCESS, entryToView);

        Model expectedModel = new ModelManager(modelMultipleEntries.getFoodDiary(), new UserPrefs());
        showEntry(expectedModel, entryToView);

        assertCommandSuccess(viewCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    /**
     * Checks if commandException thrown if given invalid index number for filtered entry list
     */
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEntriesAtIndexes(modelMultipleEntries, INDEX_FIRST_ENTRY, INDEX_SECOND_ENTRY);

        Index outOfBoundIndex = INDEX_THIRD_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < modelMultipleEntries.getFoodDiary().getEntryList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        //Check when model has multiple entries (to check for plural message)
        assertCommandFailure(viewCommand, modelMultipleEntries,
                String.format(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));


        showEntryAtIndex(modelMultipleEntries, INDEX_FIRST_ENTRY);

        outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < modelMultipleEntries.getFoodDiary().getEntryList().size());

        viewCommand = new ViewCommand(outOfBoundIndex);

        //Check when model has single entry (to check for singular message)
        assertCommandFailure(viewCommand, modelMultipleEntries,
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
    }

    @Test
    void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_ENTRY);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_ENTRY);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_ENTRY);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different entry -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to specified entry.
     */
    private void showEntry(Model model, Entry entry) {
        model.updateFilteredEntryList(p -> p.isSameEntry(entry));

        assertTrue(!model.getFilteredEntryList().isEmpty());
    }
}
