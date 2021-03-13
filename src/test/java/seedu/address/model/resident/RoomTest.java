package seedu.address.model.resident;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Room(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        String invalidRoom = "03-1234";
        assertThrows(IllegalArgumentException.class, () -> new Room(invalidRoom));
    }

    @Test
    public void isValidRoom() {
        // null room
        assertThrows(NullPointerException.class, () -> Room.isValidRoom(null));

        // invalid rooms
        assertFalse(Room.isValidRoom("")); // empty string
        assertFalse(Room.isValidRoom("-")); // no numbers
        assertFalse(Room.isValidRoom("03144")); // room does not have hyphen
        assertFalse(Room.isValidRoom("floor-unit")); // non-numeric
        assertFalse(Room.isValidRoom("fl00r-un1t")); // alphabets with number
        assertFalse(Room.isValidRoom("0-123")); // floor is fewer than 2 digits
        assertFalse(Room.isValidRoom("000-123")); // floor is more than 2 digits
        assertFalse(Room.isValidRoom("00-12")); // unit is few than 3 digits
        assertFalse(Room.isValidRoom("00-1234")); // unit is more than 3 digits


        // valid rooms
        assertTrue(Room.isValidRoom("01-234"));
    }
}
