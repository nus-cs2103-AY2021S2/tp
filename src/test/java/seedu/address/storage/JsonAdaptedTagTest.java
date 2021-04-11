package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {
    private static final String VALID_TAG_1 = "Completing";
    private static final String VALID_TAG_2 = "Important";
    private static final String VALID_TAG_3 = "Failing";
    private static final String VALID_TAG_4 = "Coursework";

    private static final String INVALID_TAG_1 = "How?";
    private static final String INVALID_TAG_2 = "**IMPORTANT";
    private static final String INVALID_TAG_3 = "--No 123";
    private static final String INVALID_TAG_4 = "This_INVALID";

    @Test
    public void toModelType_ValidTagTest() {
        //valid tag
        assertTrue(Tag.isValidTagName(VALID_TAG_1)); // CS 1k mod
        assertTrue(Tag.isValidTagName(VALID_TAG_2)); // CS 1k mod
        assertTrue(Tag.isValidTagName(VALID_TAG_3)); // CS 3k mod
        assertTrue(Tag.isValidTagName(VALID_TAG_4)); // CS 4k mod

        //invalid tags
        assertFalse(Tag.isValidTagName(INVALID_TAG_1)); // Special character at the back
        assertFalse(Tag.isValidTagName(INVALID_TAG_2)); // Special character at the front
        assertFalse(Tag.isValidTagName(INVALID_TAG_3)); // special character at the front, alphanumeric, with space
        assertFalse(Tag.isValidTagName(INVALID_TAG_4)); // special character in the middle


    }

    @Test
    public void toModel_invalidTagTest_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(INVALID_TAG_1);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModel_notEqualTagTest() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_TAG_1);
        JsonAdaptedTag tag2 = new JsonAdaptedTag(VALID_TAG_2);
        assertFalse(tag.equals(tag2));

    }



}
