package seedu.flashback.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.Assert.assertThrows;

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
        // null address
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priorities
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("mid"));
        assertFalse(Priority.isValidPriority("average"));

        // valid priorities
        assertTrue(Priority.isValidPriority("High"));
        assertTrue(Priority.isValidPriority("Mid"));
        assertTrue(Priority.isValidPriority("Low"));
    }
}
