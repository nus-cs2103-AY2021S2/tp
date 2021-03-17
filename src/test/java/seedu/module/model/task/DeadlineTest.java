package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void deadlineComparator_earlierDeadlineIsPutInFront() {
        Deadline deadline1 = new Deadline("2021-01-01 12:00");
        Deadline deadline2 = new Deadline("2021-01-02 12:00");
        Deadline deadline3 = new Deadline("2021-01-02 11:00");
        Deadline deadline4 = new Deadline("2020-12-02 23:59");
        assertEquals(deadline1.compareTo(deadline2), -1);
        assertEquals(deadline2.compareTo(deadline3), 1);
        assertEquals(deadline3.compareTo(deadline4), 1);
    }

    @Test
    public void isValidDeadline() {
        // null deadline number
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline numbers
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("2001-13-01 12:00")); // Date is out of range
        assertFalse(Deadline.isValidDeadline("2001-01-02 34:00")); // Time is out of range
        assertFalse(Deadline.isValidDeadline("23:59")); // only HH:mm
        assertFalse(Deadline.isValidDeadline("2001-01-02")); // only dates

        // valid deadline numbers
        assertTrue(Deadline.isValidDeadline("2001-01-02 12:00"));
        assertTrue(Deadline.isValidDeadline("2015-01-02 23:00"));
        assertTrue(Deadline.isValidDeadline("2021-02-02 23:59"));
    }
}
