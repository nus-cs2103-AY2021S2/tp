package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGarmentAtIndex;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GARMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.garment.Garment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalWardrobe(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Garment garmentToDelete = model.getFilteredGarmentList().get(INDEX_FIRST_GARMENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_GARMENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_GARMENT_SUCCESS, garmentToDelete);

        ModelManager expectedModel = new ModelManager(model.getWardrobe(), new UserPrefs());
        expectedModel.deleteGarment(garmentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGarmentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showGarmentAtIndex(model, INDEX_FIRST_GARMENT);

        Garment garmentToDelete = model.getFilteredGarmentList().get(INDEX_FIRST_GARMENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_GARMENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_GARMENT_SUCCESS, garmentToDelete);

        Model expectedModel = new ModelManager(model.getWardrobe(), new UserPrefs());
        expectedModel.deleteGarment(garmentToDelete);
        showNoGarment(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGarmentAtIndex(model, INDEX_FIRST_GARMENT);

        Index outOfBoundIndex = INDEX_SECOND_GARMENT;
        // ensures that outOfBoundIndex is still in bounds of wardrobe list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWardrobe().getGarmentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_GARMENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_GARMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_GARMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different garment -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGarment(Model model) {
        model.updateFilteredGarmentList(p -> false);

        assertTrue(model.getFilteredGarmentList().isEmpty());
    }
}
