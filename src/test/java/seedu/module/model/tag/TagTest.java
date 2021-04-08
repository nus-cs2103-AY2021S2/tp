package seedu.module.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

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
    }

    @Test
    public void equals() {
        Tag firstTag = new Tag("FirstTag");

        //EP: Same Tag object
        assertTrue(firstTag.equals(firstTag));

        //EP: Different Tag object, same lowercase name
        Tag firstTagOther = new Tag("firstTag");
        assertTrue(firstTag.equals(firstTagOther));

        //EP: Different Tag object, different name
        Tag secondTag = new Tag("SecondTag");
        assertFalse(firstTag.equals(secondTag));

        //EP: Not a tag object
        String tagString = "NotTag";
        assertFalse(firstTag.equals(tagString));
    }

    @Test
    public void hashCodeTest() {
        String firstTagLowerCase = "firsttag";
        Tag firstTag = new Tag("FirstTag");

        assertEquals(firstTag.hashCode(), firstTagLowerCase.hashCode());
    }
}
