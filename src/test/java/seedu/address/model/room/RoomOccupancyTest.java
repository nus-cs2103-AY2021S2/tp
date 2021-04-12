package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code RoomOccupancy}.
 */
public class RoomOccupancyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IsOccupied(null));
    }

    @Test
    public void constructor_invalidIsOccupied_throwsIllegalArgumentException() {
        String invalidOccupancyStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new IsOccupied(invalidOccupancyStatus));
    }

    @Test
    public void isValidRoomNumber() {
        // null occupancy status
        assertThrows(NullPointerException.class, () -> IsOccupied.isValidOccupancyStatus(null));

        // invalid occupancy status
        assertFalse(IsOccupied.isValidOccupancyStatus("")); // empty string
        assertFalse(IsOccupied.isValidOccupancyStatus(" ")); // spaces only
        assertFalse(IsOccupied.isValidOccupancyStatus("^")); // only non-alphanumeric characters
        assertFalse(IsOccupied.isValidOccupancyStatus("X")); // Not Y or N
        assertFalse(IsOccupied.isValidOccupancyStatus("Yak")); // Multiple characters starting with Y
        assertFalse(IsOccupied.isValidOccupancyStatus("Norway")); // Multiple characters starting with N

        // valid occupancy status
        assertTrue(IsOccupied.isValidOccupancyStatus("Y"));
        assertTrue(IsOccupied.isValidOccupancyStatus("N"));
    }

    @Test
    public void equals() {
        IsOccupied isOccupied = new IsOccupied("Y");
        // EP: same object -> return true
        assertTrue(isOccupied.equals(isOccupied));

        IsOccupied anotherIsOccupied = new IsOccupied("Y");
        // EP: diff object, same values -> return true
        assertTrue(isOccupied.equals(isOccupied));

        anotherIsOccupied = new IsOccupied("N");
        // EP: diff object, diff values -> return false
        assertFalse(isOccupied.equals(anotherIsOccupied));

    }
}
