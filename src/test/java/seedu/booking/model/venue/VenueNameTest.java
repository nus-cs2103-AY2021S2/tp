package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VenueNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VenueName(null));
    }

    @Test
    public void constructor_invalidVenueName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new VenueName(invalidName));
    }

    @Test
    public void isValidVenueName() {
        // null venue name
        assertThrows(NullPointerException.class, () -> VenueName.isValidVenueName(null));

        // invalid venue name
        assertFalse(VenueName.isValidVenueName("")); // empty string
        assertFalse(VenueName.isValidVenueName(" ")); // spaces only
        assertFalse(VenueName.isValidVenueName("^")); // only non-alphanumeric characters
        assertFalse(VenueName.isValidVenueName("peter*")); // contains non-alphanumeric characters

        // valid venue name
        assertTrue(VenueName.isValidVenueName("peter jack")); // alphabets only
        assertTrue(VenueName.isValidVenueName("12345")); // numbers only
        assertTrue(VenueName.isValidVenueName("peter the 2nd")); // alphanumeric characters
        assertTrue(VenueName.isValidVenueName("Capital Tan")); // with capital letters
        assertTrue(VenueName.isValidVenueName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
