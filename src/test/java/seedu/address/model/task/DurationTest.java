package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidStartTime = " ";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidStartTime));
    }

    @Test
    public void isValidDeadline() {
        // null deadline number
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // invalid deadline numbers
        assertFalse(Duration.isValidDuration("")); // empty string
        assertFalse(Duration.isValidDuration(" ")); // spaces only


        // valid deadline numbers
        assertTrue(Duration.isValidDuration("15:30"));

    }
}
