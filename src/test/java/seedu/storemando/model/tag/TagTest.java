package seedu.storemando.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.testutil.Assert.assertThrows;

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

        // invalid tag name
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Tag.isValidTagName("coco*")); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName("coco crunch")); // contains more than one word

        // valid tag name
        assertTrue(Tag.isValidTagName("expiringsoon")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("Fruitloops2")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("FAVOURITE")); // with capital letters
        assertTrue(Tag.isValidTagName("THISisGRANDmomsFAVOURITE")); // long names
    }

    @Test
    public void equals() {
        Tag tag = new Tag(("A"));
        assertTrue(tag.equals(tag)); // same itemname object
        assertTrue(tag.equals(new Tag("A"))); // alphabets onl
        assertTrue(new Tag("123").equals(new Tag("123"))); // numbers only
        assertTrue(new Tag("favourite1").equals(new Tag("favourite1"))); // alphanumeric only

        assertFalse(new Tag("needs").equals(new Tag("need"))); // name is a prefix of other itemName
        assertFalse(new Tag("10").equals(new Tag("1"))); // number is a prefix of another
        assertFalse(new Tag("FAVOURITE").equals(new Tag("FAVOURITE1"))); // alphanumeric prefix
        assertFalse(new Tag("Need").equals(new Tag("neEd"))); // alphabets only in different cases

    }

}
