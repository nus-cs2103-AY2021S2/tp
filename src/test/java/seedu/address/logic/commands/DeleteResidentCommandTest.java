package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showResidentAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.resident.Resident;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteResidentCommand}.
 */
public class DeleteResidentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Resident residentToDelete = model.getFilteredResidentList().get(INDEX_FIRST.getZeroBased());
        DeleteResidentCommand deleteResidentCommand = new DeleteResidentCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteResidentCommand.MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteResident(residentToDelete);

        assertCommandSuccess(deleteResidentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidentList().size() + 1);
        DeleteResidentCommand deleteResidentCommand = new DeleteResidentCommand(outOfBoundIndex);

        assertCommandFailure(deleteResidentCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showResidentAtIndex(model, INDEX_FIRST);

        Resident residentToDelete = model.getFilteredResidentList().get(INDEX_FIRST.getZeroBased());
        DeleteResidentCommand deleteResidentCommand = new DeleteResidentCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteResidentCommand.MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteResident(residentToDelete);
        showNoResident(expectedModel);

        assertCommandSuccess(deleteResidentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showResidentAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getResidentList().size());

        DeleteResidentCommand deleteResidentCommand = new DeleteResidentCommand(outOfBoundIndex);

        assertCommandFailure(deleteResidentCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteResidentCommand deleteFirstCommand = new DeleteResidentCommand(INDEX_FIRST);
        DeleteResidentCommand deleteSecondCommand = new DeleteResidentCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteResidentCommand deleteFirstCommandCopy = new DeleteResidentCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different resident -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoResident(Model model) {
        model.updateFilteredResidentList(p -> false);

        assertTrue(model.getFilteredResidentList().isEmpty());
    }
}
