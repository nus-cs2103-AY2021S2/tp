package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.room.TypicalRooms.ROOM_CORRIDOR_NON_AC_OCCUPIED;
import static seedu.address.testutil.room.TypicalRooms.ROOM_SUITE_AC_NOT_OCCUPIED;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.room.RoomBuilder;

/**
 * Contains unit tests for {@code Room}.
 */
public class RoomTest {
    @Test
    public void isSameRoom() {
        // same object -> returns true
        assertTrue(ROOM_CORRIDOR_NON_AC_OCCUPIED.isSameRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED));

        // null -> returns false
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.isSameRoom(null));

        // same name, all other attributes different -> returns true
        Room editedRoomOne = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED)
                .withOccupancyStatus("N")
                .withRoomType("suite_ac")
                .build();
        assertTrue(ROOM_CORRIDOR_NON_AC_OCCUPIED.isSameRoom(editedRoomOne));

        // different room number, all other attributes same -> returns false
        editedRoomOne = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED).withRoomNumber("22-100").build();
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.isSameRoom(editedRoomOne));
    }

    @Test
    public void equals() {
        // EP: same values -> returns true
        Room roomCopy = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED).build();
        assertTrue(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(roomCopy));

        // EP: same object -> returns true
        assertTrue(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(ROOM_CORRIDOR_NON_AC_OCCUPIED));

        // EP: null -> returns false
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(null));

        // EP: different type -> returns false
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(5));

        // EP: different room -> returns false
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(ROOM_SUITE_AC_NOT_OCCUPIED));

        // EP: different room number -> returns false
        Room editedRoom = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED).withRoomNumber("14-100").build();
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(editedRoom));

        // EP: different room type -> returns false
        editedRoom = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED).withRoomType("suite_ac").build();
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(editedRoom));

        // EP: different occupancy -> returns false
        editedRoom = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED).withOccupancyStatus("N").build();
        assertFalse(ROOM_CORRIDOR_NON_AC_OCCUPIED.equals(editedRoom));
    }
}
