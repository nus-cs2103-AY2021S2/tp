package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CleanStatusTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CleanStatusTag(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CleanStatusTag(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CleanStatusTag.isValidCleanStatusTag(null));

        // invalid name
        assertFalse(CleanStatusTag.isValidCleanStatusTag("")); // empty string
        assertFalse(CleanStatusTag.isValidCleanStatusTag(" ")); // spaces only
        assertFalse(CleanStatusTag.isValidCleanStatusTag("^")); // only non-alphanumeric characters
        assertFalse(CleanStatusTag.isValidCleanStatusTag("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(CleanStatusTag.isValidCleanStatusTag("y")); // lowercase y
        assertTrue(CleanStatusTag.isValidCleanStatusTag("Y")); // uppercase Y
        assertTrue(CleanStatusTag.isValidCleanStatusTag("n")); // lowercase n
        assertTrue(CleanStatusTag.isValidCleanStatusTag("N")); // uppercase N
    }
}
