package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.attributes.Date;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date((String) null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "21/03/20199";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date number
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date numbers
        assertFalse(Date.isValidDate("21/03/20193")); // empty string
        // extra spaces
        assertFalse(Date.isValidDate("21/03/ 2019"));
        // extra line break
        assertFalse(Date.isValidDate("\n21/03/2019"));

        // valid date numbers
        assertTrue(Date.isValidDate("12/12/2021")); // exactly 3 numbers

    }
}
