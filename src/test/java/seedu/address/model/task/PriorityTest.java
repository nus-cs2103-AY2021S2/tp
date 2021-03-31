package seedu.address.model.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.EXERCISE;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {

        // invalid priority
        assertFalse(Priority.isValidPriority("")); // empty string

        // valid priority
        assertTrue(Priority.isValidPriority("0"));
        assertTrue(Priority.isValidPriority("1"));
        assertTrue(Priority.isValidPriority("3"));
        assertTrue(Priority.isValidPriority(null)); //null is valid
    }

    @Test
    public void compareTest() {
        assertEquals(ASSIGNMENT.getPriority().compareTo(EXERCISE.getPriority()),
                Integer.compare(ASSIGNMENT.getPriority().getPriority(), EXERCISE.getPriority().getPriority()));
    }
}
