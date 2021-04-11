package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomNumberTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomNumber(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        String invalidRoomNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new RoomNumber(invalidRoomNumber));
    }

    @Test
    public void isValidRoomNumber() {
        // null room number
        assertThrows(NullPointerException.class, () -> RoomNumber.isValidRoomNumber(null));

        // invalid room number
        assertFalse(RoomNumber.isValidRoomNumber("")); // empty string
        assertFalse(RoomNumber.isValidRoomNumber(" ")); // spaces only
        assertFalse(RoomNumber.isValidRoomNumber("^")); // only non-alphanumeric characters
        assertFalse(RoomNumber.isValidRoomNumber("01-010*")); // contains non-alphanumeric characters
        assertFalse(RoomNumber.isValidRoomNumber("1-010")); // format does not conform for floor number
        assertFalse(RoomNumber.isValidRoomNumber("01-00")); // format does not conform for unit number
        assertFalse(RoomNumber.isValidRoomNumber("00-00")); // disallow starting with 00

        // valid room number
        assertTrue(RoomNumber.isValidRoomNumber("12-123")); // correct format
        assertTrue(RoomNumber.isValidRoomNumber("02-123")); // correct format floor number start with 0
        assertTrue(RoomNumber.isValidRoomNumber("10-003")); // correct format unit number start with 10
        assertTrue(RoomNumber.isValidRoomNumber("22-003")); // correct format unit number start with 10
        assertTrue(RoomNumber.isValidRoomNumber("02-023")); // correct format unit number start with 0
    }

    @Test
    public void equals() {
        RoomNumber roomNumber = new RoomNumber("01-001");
        // same object -> return true
        assertTrue(roomNumber.equals(roomNumber));

        RoomNumber anotherRoomNumber = new RoomNumber("01-001");
        // diff object, same values -> return true
        assertTrue(roomNumber.equals(anotherRoomNumber));

        anotherRoomNumber = new RoomNumber("02-001");
        // diff object, diff values -> return false
        assertFalse(roomNumber.equals(anotherRoomNumber));

    }
}
