package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "21342134";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline number
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline numbers
        assertFalse(Deadline.isValidDeadline("2134234")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only


        // valid deadline numbers
        assertTrue(Deadline.isValidDeadline("12/12/2021")); // exactly 3 numbers

    }
}
