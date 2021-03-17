package seedu.us.among.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid tag
        assertFalse(Tag.isValidTagName("*abc")); // non-alphanumeric
        assertFalse(Tag.isValidTagName("-123")); // non-alphanumeric
        assertFalse(Tag.isValidTagName("")); // empty tag
        assertFalse(Tag.isValidTagName(" ")); // spaces only

        // valid tag
        assertTrue(Tag.isValidTagName("Hello"));
        assertTrue(Tag.isValidTagName("123"));
        assertTrue(Tag.isValidTagName("Hello123"));
    }

}
