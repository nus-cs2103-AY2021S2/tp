package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // invalid time
        assertFalse(Time.isValidTime("")); // empty string

        // valid times
        assertTrue(Time.isValidTime("23:59"));
        assertTrue(Time.isValidTime("12:34"));
        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime(null)); //null is valid input
    }
}
