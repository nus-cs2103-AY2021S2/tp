package seedu.taskify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null Description number
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid Description numbers
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("91")); // less than 3 numbers
        assertFalse(Description.isValidDescription("Description")); // non-numeric
        assertFalse(Description.isValidDescription("9011p041")); // alphabets within digits
        assertFalse(Description.isValidDescription("9312 1534")); // spaces within digits

        // valid Description numbers
        assertTrue(Description.isValidDescription("911")); // exactly 3 numbers
        assertTrue(Description.isValidDescription("93121534"));
        assertTrue(Description.isValidDescription("124293842033123")); // long Description numbers
    }
}
