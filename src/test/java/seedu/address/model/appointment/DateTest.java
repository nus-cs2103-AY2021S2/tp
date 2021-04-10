package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("4-07-2021")); // 1 digit in day part
        assertFalse(Date.isValidDate("04-7-2021")); // 1 digit in month part
        assertFalse(Date.isValidDate("04-07-21")); // 2 digit in year part
        assertFalse(Date.isValidDate("04/07/2021")); // using forward slash

        // valid date
        assertTrue(Date.isValidDate("04-07-2021"));
        assertTrue(Date.isValidDate("31-12-2021"));
    }

    @Test
    public void testStringConversion() {
        assertEquals("Jan 24, 2021",
                new Date(LocalDate.parse("2021-01-24")).toString());
        assertEquals("Feb 29, 2000",
                new Date(LocalDate.parse("2000-02-29")).toString());
    }
}
