package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("3PM")); // contain letters
        assertFalse(Time.isValidTime("930")); // 3 digits
        assertFalse(Time.isValidTime("01930")); // 5 digits
        assertFalse(Time.isValidTime("19:30")); // using colon

        // valid time
        assertTrue(Time.isValidTime("0845"));
        assertTrue(Time.isValidTime("1930"));
    }

    @Test
    public void testStringConversion() {
        assertEquals("9:00AM",
                new Time(LocalTime.parse("09:00")).toString());
        assertEquals("11:59PM",
                new Time(LocalTime.parse("23:59")).toString());
    }
}
