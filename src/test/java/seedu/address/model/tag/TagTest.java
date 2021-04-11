package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.COMMON_TAG;
import static seedu.address.testutil.TypicalTags.COMMON_TAG_STRING;

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
    public void equals_sameIdentityTag_returnsTrue() {
        // same tag -> returns true
        assertEquals(COMMON_TAG, COMMON_TAG);

        // tag with same name, different case -> returns true
        assertEquals(COMMON_TAG, new Tag(COMMON_TAG_STRING.toLowerCase()));
    }

}
