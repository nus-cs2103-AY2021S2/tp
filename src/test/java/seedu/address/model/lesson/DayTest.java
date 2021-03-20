package seedu.address.model.lesson;

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
        //null day
        assertThrows(NullPointerException.class, () -> Day.isValidDay(null));

        // invalid day
        assertFalse(Day.isValidDay("")); // empty string
        assertFalse(Day.isValidDay(" ")); // spaces only
        assertFalse(Day.isValidDay("^")); // only non-alphanumeric characters
        assertFalse(Day.isValidDay("hello")); // contains strings not within given range
        assertFalse(Day.isValidDay("3300")); // contains numbers
        assertFalse(Day.isValidDay("monday tuesday")); // multiple strings
        assertFalse(Day.isValidDay("monday 2")); // multiple strings with number

        // valid day
        assertTrue(Day.isValidDay("Monday")); // capitalized first letter
        assertTrue(Day.isValidDay("monday")); // lowercase
        assertTrue(Day.isValidDay("MONDAY")); // uppercase
    }
}
