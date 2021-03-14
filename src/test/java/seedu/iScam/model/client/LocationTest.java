package seedu.iScam.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iScam.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null iScam
        assertThrows(NullPointerException.class, () -> Location.isValidAddress(null));

        // invalid addresses
        assertFalse(Location.isValidAddress("")); // empty string
        assertFalse(Location.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Location.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Location.isValidAddress("-")); // one character
        assertTrue(Location.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long iScam
    }
}
