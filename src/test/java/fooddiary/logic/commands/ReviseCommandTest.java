package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandFailure;
import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.logic.commands.CommandTestUtil.showEntryAtIndex;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithMultipleEntries;
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
 * {@code ReviseCommand}.
 */
class ReviseCommandTest {

    private Model model = new ModelManager(getTypicalFoodDiaryWithMultipleEntries(), new UserPrefs());

    /**
     * Checks if given valid index number for unfiltered entry list, correct entry is returned
     */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Entry entryToRevise = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ReviseCommand reviseCommand = new ReviseCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ReviseCommand.MESSAGE_REVISE_ENTRY_SUCCESS, entryToRevise);

        ModelManager expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());

        assertCommandSuccess(reviseCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Checks if given invalid index number for unfiltered entry list throws CommandException
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEntryList().size() + 1);
        ReviseCommand reviseCommand = new ReviseCommand(outOfBoundIndex);

        assertCommandFailure(reviseCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL);
    }

    /**
     * Checks if given valid index number for filtered entry list, correct entry is returned
     */
    @Test
    public void execute_validIndexFilteredList_success() {
        showEntryAtIndex(model, INDEX_FIRST_ENTRY);

        Entry entryToRevise = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ReviseCommand reviseCommand = new ReviseCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ReviseCommand.MESSAGE_REVISE_ENTRY_SUCCESS, entryToRevise);

        Model expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());
        reviseEntry(expectedModel, entryToRevise);

        assertCommandSuccess(reviseCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Checks if commandException thrown if given invalid index number for filtered entry list
     */
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEntryAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodDiary().getEntryList().size());

        ReviseCommand reviseCommand = new ReviseCommand(outOfBoundIndex);

        assertCommandFailure(reviseCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL);
    }

    @Test
    void equals() {
        ReviseCommand reviseFirstCommand = new ReviseCommand(INDEX_FIRST_ENTRY);
        ReviseCommand reviseSecondCommand = new ReviseCommand(INDEX_SECOND_ENTRY);

        // same object -> returns true
        assertTrue(reviseFirstCommand.equals(reviseFirstCommand));

        // same values -> returns true
        ReviseCommand reviseFirstCommandCopy = new ReviseCommand(INDEX_FIRST_ENTRY);
        assertTrue(reviseFirstCommand.equals(reviseFirstCommandCopy));

        // different types -> returns false
        assertFalse(reviseFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reviseFirstCommand.equals(null));

        // different entry -> returns false
        assertFalse(reviseFirstCommand.equals(reviseSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to specified entry.
     */
    private void reviseEntry(Model model, Entry entry) {
        model.updateFilteredEntryList(p -> p.isSameEntry(entry));

        assertTrue(!model.getFilteredEntryList().isEmpty());
    }
}
