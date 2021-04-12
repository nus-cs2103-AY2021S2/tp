package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.room.Room;
import seedu.address.testutil.room.RoomBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddRoomCommand}.
 */
public class AddRoomCommandTest {
    @Test
    public void constructor_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRoomCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() throws Exception {
        Room validRoom = new RoomBuilder().build();

        assertThrows(NullPointerException.class, () -> new AddRoomCommand(validRoom).execute(null));
    }

    @Test
    public void execute_roomAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRoomAdded modelStub = new ModelStubAcceptingRoomAdded();
        Room validRoom = new RoomBuilder().build();

        CommandResult commandResult = new AddRoomCommand(validRoom).execute(modelStub);

        assertEquals(String.format(AddRoomCommand.MESSAGE_SUCCESS, validRoom), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRoom), modelStub.roomsAdded);
    }

    @Test
    public void execute_duplicateRooom_throwsCommandException() {
        // First we create a room and add that to the command. The command will eventually try to execute
        // by adding this room to the model
        Room validRoom = new RoomBuilder().build();
        AddRoomCommand addRoomCommand = new AddRoomCommand(validRoom);

        // We create a model containing the same room the command is going to try to add to the model
        ModelStub modelStub = new ModelStubWithRoom(validRoom);

        // We see that (hopefully) the command raises an exception because the model already contains this room
        assertThrows(CommandException.class,
                AddRoomCommand.MESSAGE_DUPLICATE_ROOM, () -> addRoomCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Room room1 = new RoomBuilder().withRoomNumber("01-011").build();
        Room room2 = new RoomBuilder().withRoomNumber("02-022").build();

        AddRoomCommand addRoom1Command = new AddRoomCommand(room1);
        AddRoomCommand addRoom2Command = new AddRoomCommand(room2);

        // EP: same object -> returns true
        assertTrue(addRoom1Command.equals(addRoom1Command));

        // EP: same values -> returns true
        AddRoomCommand addRoom1CommandCopy = new AddRoomCommand(room1);
        assertTrue(addRoom1Command.equals(addRoom1CommandCopy));

        // EP: different types -> returns false
        assertFalse(addRoom1Command.equals(1));

        // EP: null -> returns false
        assertFalse(addRoom1Command.equals(null));

        // EP: different room -> return false
        assertFalse(addRoom1Command.equals(addRoom2Command));
    }

    /**
     * A Model stub that contains a single room.
     */
    private class ModelStubWithRoom extends ModelStub {
        private final Room room;

        ModelStubWithRoom(Room room) {
            requireNonNull(room);
            this.room = room;
        }

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return this.room.isSameRoom(room);
        }
    }

    /**
     * A Model stub that always accepts the room being added.
     */
    private class ModelStubAcceptingRoomAdded extends ModelStub {
        final ArrayList<Room> roomsAdded = new ArrayList<>();

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return roomsAdded.stream().anyMatch(room::isSameRoom);
        }

        @Override
        public void addRoom(Room room) {
            requireNonNull(room);
            roomsAdded.add(room);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddRoomCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
