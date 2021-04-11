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
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

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

    // EPs: [invalid format] [no fields specified] [some fields specified]
    @Test
    public void execute_allFieldsSpecifiedUnfilteredRoomList_success() {
        Room roomToEdit = model.getFilteredRoomList().get(3);

        // Room we are changing to needs to maintain the occupancy status
        // of the room we start with. So we pre-set it here.
        Room editedRoom = new RoomBuilder()
                .withOccupancyStatus(roomToEdit.isOccupied().toString())
                .build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(editedRoom).build();
        // Use 4th as its not tied to any issues in the test data
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FOURTH, descriptor);

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRoom(roomToEdit, editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredRoomList_success() {
        // We used second-last room as last room is tied to an issue
        Index indexSecondLastRoom = Index.fromOneBased(model.getFilteredRoomList().size() - 1);
        Room secondLastRoom = model.getFilteredRoomList().get(indexSecondLastRoom.getZeroBased());

        RoomBuilder roomInList = new RoomBuilder(secondLastRoom);

        // Inherit the occupancy status of the room we start with, as that is guaranteed
        Room editedRoom = roomInList
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .withOccupancyStatus(secondLastRoom.isOccupied().toString())
                .build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder()
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .build();

        EditRoomCommand editRoomCommand = new EditRoomCommand(indexSecondLastRoom, descriptor);

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRoom(secondLastRoom, editedRoom);

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredRoomList_success() {
        // Use 4th as its not tied to any issues in the test data
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FOURTH,
                new EditRoomDescriptor());
        Room editedRoom = model.getFilteredRoomList().get(INDEX_FOURTH.getZeroBased());

        String expectedMessage = String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, editedRoom);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // Use 4th room as it is not tied to any issues.
        showRoomAtIndex(model, INDEX_FOURTH);

        // Use 4th as its not tied to any issues in the test data
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
        assertCommandFailure(editRoomCommand, model, String.format(
                Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX, model.getFilteredRoomList().size()));
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

        assertCommandFailure(editRoomCommand, model, String.format(
                Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX, model.getFilteredRoomList().size()));
    }

    // No more than 1 invalid input: Only room number is an illegal input here.
    /**
     * Edit room number of a room that has an issue
     */
    @Test
    public void execute_roomHasIssueChangeRoomNumber_failure() {
        // Room idx 3 is tied to issue idx 0
        Room roomToEdit = model.getFilteredRoomList().get(INDEX_SECOND.getZeroBased());

        // Room we are changing to needs to maintain the occupancy status
        // of the room we start with. So we pre-set it here.
        Room editedRoom = new RoomBuilder()
                .withOccupancyStatus(roomToEdit.isOccupied().toString())
                .build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(editedRoom)
                .withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .build();

        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_THIRD, descriptor);

        assertCommandFailure(editRoomCommand, model, EditRoomCommand.MESSAGE_ROOM_HAS_ISSUES);
    }

    // Each valid input at least once: we allow room type and room tag change at least once below to ensure they
    // can be changed despite the room having issues tied to it.
    /**
     * Edit room type of a room that has an issue
     */
    @Test
    public void execute_roomHasIssueChangeRoomType_success() {
        // Room idx 3 is tied to issue idx 0
        Room roomToEdit = model.getFilteredRoomList().get(INDEX_SECOND.getZeroBased());

        // Room we are changing to needs to maintain the occupancy status
        // of the room we start with. So we pre-set it here.
        Room editedRoom = new RoomBuilder()
                .withOccupancyStatus(roomToEdit.isOccupied().toString())
                .build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(editedRoom)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .build();

        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_THIRD, descriptor);


        assertCommandFailure(editRoomCommand, model, EditRoomCommand.MESSAGE_ROOM_HAS_ISSUES);
    }

    /**
     * Edit room type of a room that has an issue
     */
    @Test
    public void execute_roomHasIssueChangeRoomTag_success() {
        // Room idx 3 is tied to issue idx 0
        Room roomToEdit = model.getFilteredRoomList().get(INDEX_SECOND.getZeroBased());

        // Room we are changing to needs to maintain the occupancy status
        // of the room we start with. So we pre-set it here.
        Room editedRoom = new RoomBuilder()
                .withOccupancyStatus(roomToEdit.isOccupied().toString())
                .build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(editedRoom)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .withTags("tagone", "tagtwo")
                .build();

        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_THIRD, descriptor);


        assertCommandFailure(editRoomCommand, model, EditRoomCommand.MESSAGE_ROOM_HAS_ISSUES);
    }

    /**
     * Edit room while filtered list is empty
     */
    @Test
    public void execute_filteredListIsEmpty_failure() {
        Room roomToEdit = model.getFilteredRoomList().get(INDEX_FIRST.getZeroBased());
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(roomToEdit).build();
        model.updateFilteredRoomList(p -> false);
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_THIRD, descriptor);

        assertCommandFailure(editRoomCommand, model, Messages.MESSAGE_NO_ROOMS);
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
