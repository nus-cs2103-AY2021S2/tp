package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidDeadline));
    }

    @Test
    public void deadlineComparator_earlierDeadlineIsPutInFront() {
        Time deadline1 = new Time("2021-01-01 12:00");
        Time deadline2 = new Time("2021-01-02 12:00");
        Time deadline3 = new Time("2021-01-02 11:00");
        Time deadline4 = new Time("2020-12-02 23:59");
        assertEquals(deadline1.compareTo(deadline2), -1);
        assertEquals(deadline2.compareTo(deadline3), 1);
        assertEquals(deadline3.compareTo(deadline4), 1);
    }

    @Test
    public void isValidDeadline() {
        // null deadline number
        assertThrows(NullPointerException.class, () -> Time.isValidDeadline(null));

        // invalid deadline numbers
        assertFalse(Time.isValidDeadline("")); // empty string
        assertFalse(Time.isValidDeadline(" ")); // spaces only
        assertFalse(Time.isValidDeadline("2001-13-01 12:00")); // Date is out of range
        assertFalse(Time.isValidDeadline("2001-01-02 34:00")); // Time is out of range
        assertFalse(Time.isValidDeadline("23:59")); // only HH:mm
        assertFalse(Time.isValidDeadline("2021/12/20 23:59")); //Date input not yyyy-MM-dd
        assertFalse(Time.isValidDeadline("2021-02-20 1230PM")); //time input not HH:mm
        assertFalse(Time.isValidDeadline("12-Jan-2021 12:59PM")); //date not in yyyy-MM-dd
        assertFalse(Time.isValidDeadline("24/12/21 23:59")); //date not in yyyy-MM-dd
        assertFalse(Time.isValidDeadline("03-04-2021")); //invalid yyyy-MM-dd input without HH:mm

        // valid deadline numbers
        assertTrue(Time.isValidDeadline("2001-01-02 12:00"));
        assertTrue(Time.isValidDeadline("2015-01-02 23:00"));
        assertTrue(Time.isValidDeadline("2021-02-02 23:59"));
        assertTrue(Time.isValidDeadline("2021-03-17")); //to test for only date input
    }
}
