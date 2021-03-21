package seedu.address.model.lesson;

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
        //null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("^")); // contains characters that are not numbers
        assertFalse(Time.isValidTime("ten")); // contains characters that are not numbers
        assertFalse(Time.isValidTime("3300")); // not within range of 0000 to 2359
        assertFalse(Time.isValidTime("2400")); // not within range of 0000 to 2359
        assertFalse(Time.isValidTime("1370")); // not within range of 0000 to 2359

        // valid time
        assertTrue(Time.isValidTime("0900")); // leading zero
        assertTrue(Time.isValidTime("0000")); // extreme value
        assertTrue(Time.isValidTime("2359")); // extreme value
    }
}
