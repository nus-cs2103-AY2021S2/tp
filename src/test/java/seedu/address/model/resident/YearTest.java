package seedu.address.model.resident;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        String invalidYear = "";
        assertThrows(IllegalArgumentException.class, () -> new Year(invalidYear));
    }

    @Test
    public void isValidYear() {
        // null year
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // invalid years
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // spaces only
        assertFalse(Year.isValidYear("6")); // number out of range
        assertFalse(Year.isValidYear("year")); // non-numeric
        assertFalse(Year.isValidYear("Year3")); // alphabets with number
        assertFalse(Year.isValidYear(" 3 ")); // alphabets with number


        // valid years
        assertTrue(Year.isValidYear("4"));
    }
}
