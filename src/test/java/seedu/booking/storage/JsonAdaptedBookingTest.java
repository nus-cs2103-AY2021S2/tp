package seedu.booking.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.booking.storage.JsonAdaptedBooking.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.booking.testutil.Assert.assertThrows;
import static seedu.booking.testutil.TypicalBookings.BOOKING_FIELD;
import static seedu.booking.testutil.TypicalPersons.BENSON;
import static seedu.booking.testutil.TypicalVenues.FIELD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

class JsonAdaptedBookingTest {
    private static final String INVALID_VENUE = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_START = "2020 02 02 02 00";
    private static final String INVALID_END = "2020 02 02 03 00";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_VENUE = FIELD.getVenueName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_DESCRIPTION = BOOKING_FIELD.getDescription().value;
    private static final String VALID_START = BOOKING_FIELD.getBookingStart().value.toString();
    private static final String VALID_END = BOOKING_FIELD.getBookingEnd().value.toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validBookingDetails_returnsBooking() throws Exception {
        JsonAdaptedBooking booking = new JsonAdaptedBooking(BOOKING_FIELD);
        assertEquals(BOOKING_FIELD, booking.toModelType());
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(INVALID_EMAIL, VALID_VENUE,
                        VALID_DESCRIPTION, VALID_START, VALID_END, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(null, VALID_VENUE,
                        VALID_DESCRIPTION, VALID_START, VALID_END, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_invalidVenueName_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, INVALID_VENUE,
                        VALID_DESCRIPTION, VALID_START, VALID_END, VALID_TAGS);
        String expectedMessage = VenueName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullVenueName_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, null,
                        VALID_DESCRIPTION, VALID_START, VALID_END, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_invalidStart_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, VALID_VENUE,
                        VALID_DESCRIPTION, INVALID_START, VALID_END, VALID_TAGS);
        String expectedMessage = StartTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullStart_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, VALID_VENUE,
                        VALID_DESCRIPTION, null, VALID_END, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_invalidEnd_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, VALID_VENUE,
                        VALID_DESCRIPTION, VALID_START, INVALID_END, VALID_TAGS);
        String expectedMessage = EndTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullEnd_throwsIllegalValueException() {
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, VALID_VENUE,
                        VALID_DESCRIPTION, VALID_START, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedBooking booking =
                new JsonAdaptedBooking(VALID_EMAIL, VALID_VENUE,
                        VALID_DESCRIPTION, VALID_START, VALID_END, invalidTags);
        assertThrows(IllegalValueException.class, booking::toModelType);
    }
}
