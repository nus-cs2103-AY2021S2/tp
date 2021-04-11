package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.commons.core.Messages.MESSAGE_INDEX_NOT_WITHIN_LIST;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.us.among.logic.commands.CommandTestUtil.assertRemoveCommandSuccess;
import static seedu.us.among.logic.commands.CommandTestUtil.showEndpointAtIndex;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.index.Index;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveCommand}.
 */
public class RemoveCommandTest {
    private static final String MESSAGE_INVALID_FORMAT_OUT_OF_BOUND = String.format(MESSAGE_INVALID_COMMAND_ERROR,
            MESSAGE_INDEX_NOT_WITHIN_LIST, RemoveCommand.MESSAGE_USAGE);

    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Endpoint endpointToDelete = model.getFilteredEndpointList().get(INDEX_FIRST_ENDPOINT.getZeroBased());
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_ENDPOINT);

        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVE_ENDPOINT_SUCCESS, endpointToDelete);

        ModelManager expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        expectedModel.removeEndpoint(endpointToDelete);

        assertRemoveCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEndpointList().size() + 1);
        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, MESSAGE_INVALID_FORMAT_OUT_OF_BOUND);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);

        Endpoint endpointToDelete = model.getFilteredEndpointList().get(INDEX_FIRST_ENDPOINT.getZeroBased());
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_ENDPOINT);

        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVE_ENDPOINT_SUCCESS, endpointToDelete);

        Model expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        expectedModel.removeEndpoint(endpointToDelete);
        showNoEndpoint(expectedModel);

        assertRemoveCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);

        Index outOfBoundIndex = INDEX_SECOND_ENDPOINT;

        // ensures that outOfBoundIndex is still in bounds of API endpoint list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEndpointList().getEndpointList().size());

        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, MESSAGE_INVALID_FORMAT_OUT_OF_BOUND);
    }

    @Test
    public void equals() {
        RemoveCommand deleteFirstCommand = new RemoveCommand(INDEX_FIRST_ENDPOINT);
        RemoveCommand deleteSecondCommand = new RemoveCommand(INDEX_SECOND_ENDPOINT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        RemoveCommand deleteFirstCommandCopy = new RemoveCommand(INDEX_FIRST_ENDPOINT);
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
