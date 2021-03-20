package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.showRoomAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.Room;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteRoomCommand}.
 */
public class DeleteRoomCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredRoomList_success() {
        Room roomToDelete = model.getFilteredRoomList().get(INDEX_FIRST.getZeroBased());
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteRoomCommand.MESSAGE_DELETE_ROOM_SUCCESS, roomToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteRoom(roomToDelete);

        assertCommandSuccess(deleteRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredRoomList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(outOfBoundIndex);

        assertCommandFailure(deleteRoomCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredRoomList_success() {
        showRoomAtIndex(model, INDEX_FIRST);

        Room roomToDelete = model.getFilteredRoomList().get(INDEX_FIRST.getZeroBased());
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteRoomCommand.MESSAGE_DELETE_ROOM_SUCCESS, roomToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteRoom(roomToDelete);
        showNoRoom(expectedModel);

        assertCommandSuccess(deleteRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredRoomList_throwsCommandException() {
        showRoomAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getRoomList().size());

        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(outOfBoundIndex);

        assertCommandFailure(deleteRoomCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteRoomCommand deleteFirstCommand = new DeleteRoomCommand(INDEX_FIRST);
        DeleteRoomCommand deleteSecondCommand = new DeleteRoomCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteRoomCommand deleteFirstCommandCopy = new DeleteRoomCommand(INDEX_FIRST);
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
    private void showNoRoom(Model model) {
        model.updateFilteredRoomList(p -> false);

        assertTrue(model.getFilteredRoomList().isEmpty());
    }
}
