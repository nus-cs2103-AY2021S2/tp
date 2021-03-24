package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalLocationBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.UserPrefs;
import seedu.iscam.model.client.Client;

/**
 * Contains integration tests (Interaction with the Model) and unit tests for
 * {@code ShowCommand}.
 */
public class ShowCommandTest {
    private Model model = new ModelManager(getTypicalLocationBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToShow = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_CLIENT_SUCCESS, clientToShow);

        ModelManager expectedModel = new ModelManager(model.getClientBook(), new UserPrefs());
        expectedModel.setDetailedClient(clientToShow);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientToShow = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_CLIENT_SUCCESS, clientToShow);

        Model expectedModel = new ModelManager(model.getClientBook(), new UserPrefs());
        expectedModel.setDetailedClient(clientToShow);
        showClientAtIndex(expectedModel, INDEX_FIRST_CLIENT);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of iscam book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientBook().getClientList().size());

        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand(INDEX_FIRST_CLIENT);
        ShowCommand showSecondCommand = new ShowCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(INDEX_FIRST_CLIENT);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
