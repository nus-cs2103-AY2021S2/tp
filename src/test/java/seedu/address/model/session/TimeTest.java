package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null Time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("^")); // only non-alphanumeric characters
        assertFalse(Time.isValidTime("math")); // only alphabets
        assertFalse(Time.isValidTime("1230")); // missing hour-minute seperator
        assertFalse(Time.isValidTime("12:30^")); // contains invalid non-alphanumeric character
        assertFalse(Time.isValidTime("12:30pm")); // contains alphabet
        assertFalse(Time.isValidTime("24:00")); // invalid hour
        assertFalse(Time.isValidTime("25:00")); // invalid hour
        assertFalse(Time.isValidTime("12:60")); // invalid minute
        assertFalse(Time.isValidTime("12:75")); // invalid minute

        // valid Time
        assertTrue(Time.isValidTime("12:30")); // normal time
        assertTrue(Time.isValidTime("00:30")); // hour corner case
        assertTrue(Time.isValidTime("23:30")); // hour corner case
        assertTrue(Time.isValidTime("00:00")); // minute corner case
        assertTrue(Time.isValidTime("23:59")); // minute corner case
    }
}
