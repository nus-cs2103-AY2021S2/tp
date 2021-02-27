package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.TypicalIndexes;
import seedu.us.among.testutil.TypicalEndpoints;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalEndpoints.getTypicalEndpointList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Endpoint endpointToDelete = model.getFilteredEndpointList().get(TypicalIndexes.INDEX_FIRST_ENDPOINT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENDPOINT_SUCCESS, endpointToDelete);

        ModelManager expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        expectedModel.deleteEndpoint(endpointToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEndpointList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showEndpointAtIndex(model, TypicalIndexes.INDEX_FIRST_ENDPOINT);

        Endpoint endpointToDelete = model.getFilteredEndpointList().get(TypicalIndexes.INDEX_FIRST_ENDPOINT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENDPOINT_SUCCESS, endpointToDelete);

        Model expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        expectedModel.deleteEndpoint(endpointToDelete);
        showNoEndpoint(expectedModel);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showEndpointAtIndex(model, TypicalIndexes.INDEX_FIRST_ENDPOINT);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_ENDPOINT;
        // ensures that outOfBoundIndex is still in bounds of API endpoint list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEndpointList().getEndpointList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_ENDPOINT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different endpoint -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEndpoint(Model model) {
        model.updateFilteredEndpointList(p -> false);

        assertTrue(model.getFilteredEndpointList().isEmpty());
    }
}
