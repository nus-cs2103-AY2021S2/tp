package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.assertClientCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.user.UserPrefs;

/**
 * Contains integration tests (Interaction with the Model) and unit tests for
 * {@code ShowCommand}.
 */
public class ShowCommandTest {
    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToShow = model.getFilteredClientList().get(INDEX_FIRST_ITEM.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_CLIENT_SUCCESS, clientToShow);

        ModelManager expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.setDetailedClient(clientToShow);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertClientCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_ITEM);

        Client clientToShow = model.getFilteredClientList().get(INDEX_FIRST_ITEM.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_CLIENT_SUCCESS, clientToShow);

        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.setDetailedClient(clientToShow);
        showClientAtIndex(expectedModel, INDEX_FIRST_ITEM);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of iscam book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientBook().getClientList().size());

        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertClientCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand(INDEX_FIRST_ITEM);
        ShowCommand showSecondCommand = new ShowCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(INDEX_FIRST_ITEM);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
