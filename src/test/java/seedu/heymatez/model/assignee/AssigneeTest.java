package seedu.heymatez.model.assignee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Assignee}.
 */
public class AssigneeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assignee(null));
    }

    @Test
    public void constructor_invalidAssigneeName_throwsIllegalArgumentException() {
        String invalidAssigneeName = "";
        assertThrows(IllegalArgumentException.class, () -> new Assignee(invalidAssigneeName));
    }

    @Test
    public void isValidAssigneeName() {
        // null assignee name
        assertThrows(NullPointerException.class, () -> Assignee.isValidAssigneeName(null));

        // invalid assignee name
        assertFalse(Assignee.isValidAssigneeName("")); // empty string
        assertFalse(Assignee.isValidAssigneeName(" ")); // spaces only
        assertFalse(Assignee.isValidAssigneeName("^")); // only non-alphanumeric characters
        assertFalse(Assignee.isValidAssigneeName("tom*")); // contains non-alphanumeric characters

        // valid assignee name
        assertTrue(Assignee.isValidAssigneeName("michelle lee")); // alphabets only
        assertTrue(Assignee.isValidAssigneeName("12345")); // numbers only
        assertTrue(Assignee.isValidAssigneeName("george the 1st")); // alphanumeric characters
        assertTrue(Assignee.isValidAssigneeName("Timothy Tan")); // with capital letters
        assertTrue(Assignee.isValidAssigneeName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
