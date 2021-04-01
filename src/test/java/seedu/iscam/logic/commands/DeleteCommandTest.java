package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalLocationBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.UserPrefs;
import seedu.iscam.model.client.Client;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalLocationBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        ModelManager expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.deleteClient(clientToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.deleteClient(clientToDelete);
        showNoClient(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of iscam book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientBook().getClientList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_CLIENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_CLIENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoClient(Model model) {
        model.updateFilteredClientList(p -> false);

        assertTrue(model.getFilteredClientList().isEmpty());
    }
}
