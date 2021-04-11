package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("4-07-2021")); // 1 digit in day part
        assertFalse(Deadline.isValidDeadline("04-7-2021")); // 1 digit in month part
        assertFalse(Deadline.isValidDeadline("04-07-21")); // 2 digit in year part
        assertFalse(Deadline.isValidDeadline("04/07/2021")); // using forward slash

        // valid deadline
        assertTrue(Deadline.isValidDeadline("04-07-2021"));
        assertTrue(Deadline.isValidDeadline("31-12-2021"));
    }

    @Test
    public void testStringConversion() {
        assertEquals("Jan 24, 2021",
                new Deadline(LocalDate.parse("2021-01-24")).toString());
        assertEquals("Feb 29, 2000",
                new Deadline(LocalDate.parse("2000-02-29")).toString());
    }
}
