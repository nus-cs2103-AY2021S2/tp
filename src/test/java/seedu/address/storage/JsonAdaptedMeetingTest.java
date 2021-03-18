package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.model.person.PersonName;


public class JsonAdaptedMeetingTest {
    private static final String INVALID_NAME = "NANI%";
    private static final String INVALID_PRIORITY = "7";
    private static final String INVALID_START = "2020:5:6 42:25";
    private static final String INVALID_END = "2020-4-0 99:99";
    private static final String INVALID_TAG = "@What";

    private static final String VALID_NAME = MEETING1.getName().fullName;
    private static final String VALID_DESCRIPTION = MEETING1.getDescription().fullDescription;
    private static final String VALID_START = MEETING1.getStart().toIsoFormatString();
    private static final String VALID_END = MEETING1.getTerminate().toIsoFormatString();
    private static final String VALID_PRIORITY = MEETING1.getPriority().toString();


    private static final List<JsonAdaptedGroup> VALID_TAGS = MEETING1.getGroups().stream()
            .map(JsonAdaptedGroup::new)
            .collect(Collectors.toList());
    @Test
    public void toModelType_validMeetingDetails_success() throws Exception {
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(MEETING1);
        assertEquals(jsonMeeting.toModelType(), MEETING1);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(INVALID_NAME, VALID_START, VALID_END,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = PersonName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(null, VALID_START, VALID_END,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, MeetingName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME, INVALID_START, VALID_END,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME , null, VALID_END,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME, VALID_START, INVALID_END,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }
    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME , VALID_START, null,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME , VALID_START, VALID_END,
                        null, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME, VALID_START, VALID_END,
                        VALID_DESCRIPTION, INVALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME , VALID_START, VALID_END,
                        VALID_DESCRIPTION, null, VALID_TAGS);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedGroup> invalidJsonGroups = new ArrayList<>(VALID_TAGS);
        invalidJsonGroups.add(new JsonAdaptedGroup(INVALID_TAG));
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME, VALID_START, VALID_END,
                        VALID_DESCRIPTION, VALID_PRIORITY, invalidJsonGroups);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, jsonMeeting::toModelType);
    }

    @Test
    public void toModelType_swappedStartEndTimes_throwsIllegalValueException() {
        assertTrue(VALID_END.compareTo(VALID_START) == 1);
        JsonAdaptedMeeting jsonMeeting =
                new JsonAdaptedMeeting(VALID_NAME , VALID_END, VALID_START,
                        VALID_DESCRIPTION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Meeting.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonMeeting::toModelType);
    }



}
