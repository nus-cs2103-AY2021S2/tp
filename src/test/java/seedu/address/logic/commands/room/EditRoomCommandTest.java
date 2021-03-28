package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_DESCRIPTOR_ONE;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_DESCRIPTOR_TWO;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_NUMBER_ONE;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_TYPES;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.showRoomAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.Room;
import seedu.address.testutil.room.EditRoomDescriptorBuilder;
import seedu.address.testutil.room.RoomBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditRoomCommand.
 */
public class EditRoomCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredRoomList_success() {
        Room editedRoom = new RoomBuilder().build();
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(editedRoom).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRoom(model.getFilteredRoomList().get(0), editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredRoomList_success() {
        Index indexLastRoom = Index.fromOneBased(model.getFilteredRoomList().size());
        Room lastRoom = model.getFilteredRoomList().get(indexLastRoom.getZeroBased());

        RoomBuilder roomInList = new RoomBuilder(lastRoom);
        Room editedRoom = roomInList
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .build();

        EditRoomCommand editRoomCommand = new EditRoomCommand(indexLastRoom, descriptor);

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRoom(lastRoom, editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredRoomList_success() {
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST,
                new EditRoomDescriptor());
        Room editedRoom = model.getFilteredRoomList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showRoomAtIndex(model, INDEX_FIRST);

        Room roomInFilteredList = model.getFilteredRoomList().get(INDEX_FIRST.getZeroBased());
        Room editedRoom = new RoomBuilder(roomInFilteredList).withRoomNumber(VALID_ROOM_NUMBER_ONE).build();
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withRoomNumber(VALID_ROOM_NUMBER_ONE).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRoom(model.getFilteredRoomList().get(0), editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRoomUnfilteredList_failure() {
        Room firstRoom = model.getFilteredRoomList().get(INDEX_FIRST.getZeroBased());
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(firstRoom).build();

        // Edit the second room to become the same as the first room
        // This will cause a duplicate
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editRoomCommand, model, EditRoomCommand.MESSAGE_DUPLICATE_ROOM);
    }

    @Test
    public void execute_duplicateRoomFilteredList_failure() {
        showRoomAtIndex(model, INDEX_FIRST);

        // edit room in filtered list into a duplicate in address book
        Room roomInList = model.getAddressBook().getRoomList().get(INDEX_SECOND.getZeroBased());
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(roomInList).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST, descriptor);

        assertCommandFailure(editRoomCommand, model, EditRoomCommand.MESSAGE_DUPLICATE_ROOM);
    }

    @Test
    public void execute_invalidRoomIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .build();

        EditRoomCommand editRoomCommand = new EditRoomCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editRoomCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidRoomIndexFilteredList_failure() {
        showRoomAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getRoomList().size());

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editRoomCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditRoomCommand standardCommand = new EditRoomCommand(INDEX_FIRST, VALID_ROOM_DESCRIPTOR_ONE);

        // same values -> returns true
        EditRoomDescriptor copyDescriptor = new EditRoomDescriptor(VALID_ROOM_DESCRIPTOR_ONE);
        EditRoomCommand commandWithSameValues = new EditRoomCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> return false
        assertFalse(standardCommand.equals(null));

        // different types -> return false;
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> return false
        assertFalse(standardCommand.equals(new EditRoomCommand(INDEX_SECOND, VALID_ROOM_DESCRIPTOR_ONE)));

        // different index -> return false
        assertFalse(standardCommand.equals(new EditRoomCommand(INDEX_FIRST, VALID_ROOM_DESCRIPTOR_TWO)));
    }

}
