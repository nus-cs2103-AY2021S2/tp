package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandFailure;
import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.logic.commands.CommandTestUtil.showEntryAtIndex;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
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

    private Model model = new ModelManager(getTypicalFoodDiary(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Entry entryToView = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_ENTRY_SUCCESS, entryToView);

        ModelManager expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEntryList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEntryAtIndex(model, INDEX_FIRST_ENTRY);

        Entry entryToView = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_ENTRY_SUCCESS, entryToView);

        Model expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());
        showEntry(expectedModel, entryToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEntryAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodDiary().getEntryList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
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
