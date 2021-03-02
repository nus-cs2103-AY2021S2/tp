package seedu.address.model.task;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority
        assertFalse(Priority.isValidPriority("")); // empty string

        // valid priority
        assertTrue(Priority.isValidPriority("0"));
        assertTrue(Priority.isValidPriority("1"));
        assertTrue(Priority.isValidPriority("3"));
    }
}
