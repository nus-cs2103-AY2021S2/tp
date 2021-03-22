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
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new VenueName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> VenueName.isValidName(null));

        // invalid name
        assertFalse(VenueName.isValidName("")); // empty string
        assertFalse(VenueName.isValidName(" ")); // spaces only
        assertFalse(VenueName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(VenueName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(VenueName.isValidName("peter jack")); // alphabets only
        assertTrue(VenueName.isValidName("12345")); // numbers only
        assertTrue(VenueName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(VenueName.isValidName("Capital Tan")); // with capital letters
        assertTrue(VenueName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
