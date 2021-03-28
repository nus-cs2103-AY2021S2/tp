package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CAMP;
import static seedu.address.testutil.TypicalEvents.EVENTONE;

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
        // null email
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime(""));
        assertFalse(Time.isValidTime(":00"));
        assertFalse(Time.isValidTime("23:"));
        assertFalse(Time.isValidTime("24:00"));
        assertFalse(Time.isValidTime("23:60"));

        // valid times
        assertTrue(Time.isValidTime("23:59"));
        assertTrue(Time.isValidTime("12:34"));
        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime("1:1"));
        assertTrue(Time.isValidTime("0:0"));
        assertTrue(Time.isValidTime("9:9"));
    }

    @Test
    public void compareTest() {
        assertEquals(CAMP.getStartTime().compareTo(EVENTONE.getStartTime()),
                CAMP.getStartTime().time.compareTo(EVENTONE.getStartTime().time));
    }
}
