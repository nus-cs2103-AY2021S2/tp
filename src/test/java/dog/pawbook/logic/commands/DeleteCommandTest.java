package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.CommandTestUtil.showOwnerAtIndex;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_FIRST_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_SECOND_OWNER;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.core.index.Index;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.owner.Owner;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Owner ownerToDelete = model.getFilteredOwnerList().get(INDEX_FIRST_OWNER.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OWNER);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_OWNER_SUCCESS, ownerToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteOwner(ownerToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOwnerList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOwnerAtIndex(model, INDEX_FIRST_OWNER);

        Owner ownerToDelete = model.getFilteredOwnerList().get(INDEX_FIRST_OWNER.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OWNER);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_OWNER_SUCCESS, ownerToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteOwner(ownerToDelete);
        showNoOwner(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOwnerAtIndex(model, INDEX_FIRST_OWNER);

        Index outOfBoundIndex = INDEX_SECOND_OWNER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOwnerList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_OWNER);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_OWNER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_OWNER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different owner -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoOwner(Model model) {
        model.updateFilteredOwnerList(p -> false);

        assertTrue(model.getFilteredOwnerList().isEmpty());
    }
}
