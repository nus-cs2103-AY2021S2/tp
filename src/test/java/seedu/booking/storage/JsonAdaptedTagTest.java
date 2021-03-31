package seedu.booking.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.booking.storage.JsonAdaptedTag.INVALID_FIELD_MESSAGE_FORMAT;
import static seedu.booking.testutil.Assert.assertThrows;
import static seedu.booking.testutil.TypicalVenues.HALL;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.exceptions.IllegalValueException;

public class JsonAdaptedTagTest {
    private static final String INVALID_TAG1 = "Ind@@rs";
    private static final String INVALID_TAG2 = "Indoors always";

    @Test
    public void toModelType_validTag_returnsVenue() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(HALL.getTags().iterator().next());
        assertEquals(HALL.getTags().iterator().next(), tag.toModelType());
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        // tags containing non alpha-numeric characters
        JsonAdaptedTag tag1 =
                new JsonAdaptedTag(INVALID_TAG1);
        String expectedMessage = INVALID_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, tag1::toModelType);

        JsonAdaptedTag tag2 =
                new JsonAdaptedTag(INVALID_TAG2);
        assertThrows(IllegalValueException.class, expectedMessage, tag2::toModelType);
    }



}
