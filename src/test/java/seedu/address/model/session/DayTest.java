package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String invalidDay = "";
        assertThrows(IllegalArgumentException.class, () -> new Day(invalidDay));
    }

    @Test
    public void isValidDay() {
        // null day
        assertThrows(NullPointerException.class, () -> Day.isValidDay(null));

        // invalid day
        assertFalse(Day.isValidDay("")); // empty string
        assertFalse(Day.isValidDay(" ")); // spaces only
        assertFalse(Day.isValidDay("^")); // only non-alphanumeric characters
        assertFalse(Day.isValidDay("wednesday*")); // contains non-alphanumeric characters
        assertFalse(Day.isValidDay("someday")); // invalid day

        // valid day
        assertTrue(Day.isValidDay("monday")); // lowercase only
        assertTrue(Day.isValidDay("TUESDAY")); // uppercase only
        assertTrue(Day.isValidDay("Wednesday")); // title case
        assertTrue(Day.isValidDay("tHurSDay")); // mix
    }
}
