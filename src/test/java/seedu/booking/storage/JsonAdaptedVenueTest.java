package seedu.booking.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.booking.storage.JsonAdaptedVenue.INVALID_FIELD_MESSAGE_FORMAT;
import static seedu.booking.storage.JsonAdaptedVenue.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.booking.testutil.Assert.assertThrows;
import static seedu.booking.testutil.TypicalVenues.FIELD;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.exceptions.IllegalValueException;

public class JsonAdaptedVenueTest {
    private static final String INVALID_CAPACITY1 = "0";
    private static final String INVALID_CAPACITY2 = "-1";

    private static final String VALID_NAME = FIELD.getVenueName().toString();
    private static final String VALID_CAPACITY = FIELD.getCapacity().toString();
    private static final String VALID_DESCRIPTION = FIELD.getDescription();


    @Test
    public void toModelType_validVenueDetails_returnsVenue() throws Exception {
        JsonAdaptedVenue venue = new JsonAdaptedVenue(FIELD);
        assertEquals(FIELD, venue.toModelType());
    }

    @Test
    public void toModelType_invalidCapacity_throwsIllegalValueException() {
        // venue with invalid capacity of 0
        JsonAdaptedVenue venue =
                new JsonAdaptedVenue(VALID_NAME, INVALID_CAPACITY1, VALID_DESCRIPTION, null);
        String expectedMessage = String.format(INVALID_FIELD_MESSAGE_FORMAT, "capacity");
        assertThrows(IllegalArgumentException.class, expectedMessage, venue::toModelType);

        // venue with invalid capacity of -1
        JsonAdaptedVenue venue2 =
                new JsonAdaptedVenue(VALID_NAME, INVALID_CAPACITY2, VALID_DESCRIPTION, null);
        String expectedMessage2 = String.format(INVALID_FIELD_MESSAGE_FORMAT, "capacity");
        assertThrows(IllegalArgumentException.class, expectedMessage2, venue2::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedVenue venue =
                new JsonAdaptedVenue(null, VALID_CAPACITY, VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, venue::toModelType);
    }

    @Test
    public void toModelType_nullCapacity_throwsIllegalValueException() {
        JsonAdaptedVenue venue = new JsonAdaptedVenue(VALID_NAME, null, VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "capacity");
        assertThrows(IllegalValueException.class, expectedMessage, venue::toModelType);
    }
}
