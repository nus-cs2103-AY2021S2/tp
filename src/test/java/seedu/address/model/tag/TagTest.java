package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName1 = "";
        String invalidTagName2 = "q";
        String invalidTagName3 = "a-";
        String invalidTagName4 = "-b";
        String invalidTagName5 = "c_";
        String invalidTagName6 = "_d";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName1));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName2));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName3));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName4));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName5));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName6));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        assertTrue(Tag.isValidTagName("ma"));
        assertTrue(Tag.isValidTagName("Ma"));
        assertTrue(Tag.isValidTagName("MA"));
        assertTrue(Tag.isValidTagName("a-b"));
        assertTrue(Tag.isValidTagName("a_b"));
        assertTrue(Tag.isValidTagName("apple"));
        assertFalse(Tag.isValidTagName("a"));
        assertFalse(Tag.isValidTagName("B"));
        assertFalse(Tag.isValidTagName("abcd_"));
        assertFalse(Tag.isValidTagName("_abcd"));
        assertFalse(Tag.isValidTagName("-abcd"));
        assertFalse(Tag.isValidTagName("abcd-"));
        assertFalse(Tag.isValidTagName("-_-"));
        assertFalse(Tag.isValidTagName("_-_"));
    }

}
