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
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = " ";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null duration number
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // invalid duration numbers
        assertFalse(Duration.isValidDuration(" ")); // spaces only

        // valid duration numbers
        assertTrue(Duration.isValidDuration("")); // empty string
        assertTrue(Duration.isValidDuration("15:30-16:30"));

    }
}
