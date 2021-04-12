package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showResidenceAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.residence.Residence;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Residence residenceToDelete = model.getFilteredResidenceList().get(INDEX_FIRST_RESIDENCE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_RESIDENCE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RESIDENCE_SUCCESS, residenceToDelete);

        ModelManager expectedModel = new ModelManager(model.getResidenceTracker(), new UserPrefs());
        expectedModel.deleteResidence(residenceToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidenceList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showResidenceAtIndex(model, INDEX_FIRST_RESIDENCE);

        Residence residenceToDelete = model.getFilteredResidenceList().get(INDEX_FIRST_RESIDENCE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_RESIDENCE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RESIDENCE_SUCCESS, residenceToDelete);

        Model expectedModel = new ModelManager(model.getResidenceTracker(), new UserPrefs());
        expectedModel.deleteResidence(residenceToDelete);
        showNoResidence(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showResidenceAtIndex(model, INDEX_FIRST_RESIDENCE);

        Index outOfBoundIndex = INDEX_SECOND_RESIDENCE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_RESIDENCE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_RESIDENCE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_RESIDENCE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoResidence(Model model) {
        model.updateFilteredResidenceList(p -> false);

        assertTrue(model.getFilteredResidenceList().isEmpty());
    }
}
