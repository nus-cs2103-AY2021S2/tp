package seedu.iscam.storage.meeting;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.iscam.storage.meeting.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.iscam.testutil.Assert.assertThrows;
import static seedu.iscam.testutil.TypicalMeetings.BENSON_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DATETIME = "example.com";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_TAG = "#friends";

    private static final String VALID_NAME = BENSON_1.getClientName().toString();
    private static final String VALID_DATETIME = BENSON_1.getDateTime().toString();
    private static final String VALID_DESCRIPTION = BENSON_1.getDescription().toString();
    private static final String VALID_LOCATION = BENSON_1.getLocation().toString();
    private static final List<JsonAdaptedMeetingTag> VALID_TAGS = BENSON_1.getTags().stream()
            .map(JsonAdaptedMeetingTag::new)
            .collect(Collectors.toList());
    private static final String VALID_STATUS = valueOf(BENSON_1.getStatus().toString());



    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(BENSON_1);
        assertEquals(BENSON_1, meeting.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(INVALID_NAME, VALID_DATETIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(null, VALID_DATETIME, VALID_LOCATION,
                VALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_NAME, INVALID_DATETIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = DateTime.MESSAGE_INVALID_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_NAME, null, VALID_LOCATION,
                VALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_NAME, VALID_DATETIME, INVALID_LOCATION,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_NAME, VALID_DATETIME, null,
                VALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_NAME, VALID_DATETIME, VALID_LOCATION,
                        INVALID_DESCRIPTION, VALID_TAGS, VALID_STATUS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_NAME, VALID_DATETIME, VALID_LOCATION,
                null, VALID_TAGS, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedMeetingTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedMeetingTag(INVALID_TAG));
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_NAME, VALID_DATETIME, VALID_LOCATION,
                        VALID_DESCRIPTION, invalidTags, VALID_STATUS);
        assertThrows(IllegalValueException.class, meeting::toModelType);
    }
}
