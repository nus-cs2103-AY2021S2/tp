package seedu.address.model.common;

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
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isValidTag() {
        // invalid Tag
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Tag.isValidTagName("do * at #")); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName("CS2103SoftwareProgrammingLecture2")); // overly long names

        // valid Tag
        assertTrue(Tag.isValidTagName("lecture")); // alphabets only
        assertTrue(Tag.isValidTagName("1337")); // numbers only
        assertTrue(Tag.isValidTagName("cs2103lecture")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("OfficialMeeting")); // with capital letters
        assertTrue(Tag.isValidTagName("CS2103Software1")); // long names - hits 15 char limit
    }
}
