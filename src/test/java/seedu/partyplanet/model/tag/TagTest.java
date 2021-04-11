package seedu.partyplanet.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.testutil.Assert.assertThrows;

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
        assertFalse(Tag.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Tag.isValidTagName("alien*")); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName("tom jerry")); // contains space
        assertFalse(Tag.isValidTagName("dolphinhorsemonkeyzebrahipposlothpiglions")); // longer than 40 characters

        // valid tag name
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("CapitalAmerica")); // with capital letters
        assertTrue(Tag.isValidTagName("dolphinhorsemonkeyzebrahipposlotheslions")); // max length names: 40
    }

}
