package seedu.address.model.issue;

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
    public void constructor_invalidRoomNumber_throwsIllegalArgumentException() {
        String invalidRoomNumber = "03-1234";
        assertThrows(IllegalArgumentException.class, () -> new RoomNumber(invalidRoomNumber));
    }

    @Test
    public void isValidRoomNumber() {
        // null room
        assertThrows(NullPointerException.class, () -> RoomNumber.isValidRoomNumber(null));

        // invalid rooms
        assertFalse(RoomNumber.isValidRoomNumber("")); // empty string
        assertFalse(RoomNumber.isValidRoomNumber("-")); // no numbers
        assertFalse(RoomNumber.isValidRoomNumber("03144")); // room does not have hyphen
        assertFalse(RoomNumber.isValidRoomNumber("floor-unit")); // non-numeric
        assertFalse(RoomNumber.isValidRoomNumber("fl00r-un1t")); // alphabets with number
        assertFalse(RoomNumber.isValidRoomNumber("0-123")); // floor is less than 2 digits
        assertFalse(RoomNumber.isValidRoomNumber("000-123")); // floor is more than 2 digits
        assertFalse(RoomNumber.isValidRoomNumber("00-12")); // unit is less than 3 digits
        assertFalse(RoomNumber.isValidRoomNumber("00-1234")); // unit is more than 3 digits

        // valid rooms
        assertTrue(RoomNumber.isValidRoomNumber("01-000"));
        assertTrue(RoomNumber.isValidRoomNumber("99-999"));
    }
}
