package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CS2030;
import static seedu.address.testutil.TypicalPersons.BENSON;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Description;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "R@chel";
    // private static final String INVALID_TIME = "651234"; commented out for v1.2
    private static final String INVALID_STATUS = "ASD";
    private static final String INVALID_DESCRIPTION = "@A!example.com";
    private static final String INVALID_PRIORITY = "DSA";
    // private static final String INVALID_TAG = "#friend"; commented out for v1.2
    // private static final String INVALID_PERSON_NAME = "R@chel"; commented out for v1.2

    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();

    private static final String VALID_NAME = CS2030.getName().toString();
    // private static final String VALID_TIME_START = "01/12/2019 10:00"; commented out for v1.2
    // private static final String VALID_TIME_END = "02/01/2022 12:00"; commented out for v1.2
    // private static final String VALID_TIME_START = changeEventTimeFormat(CS2030.getTimeStart().toString());
    // private static final String VALID_TIME_END = changeEventTimeFormat(CS2030.getTimeEnd().toString());
    private static final String VALID_STATUS = CS2030.getStatus().toString();
    private static final String VALID_DESCRIPTION = CS2030.getDescription().toString();
    private static final String VALID_PRIORITY = CS2030.getPriority().toString();
    /* commented out for v1.2
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2030.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPerson> VALID_PERSONS = CS2030.getPersons().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());
     */

    /*
    private static String changeEventTimeFormat(String eventTime) {
        String[] dateAndTime = eventTime.replaceAll("[-T]", " ").split(" ");
        String date = dateAndTime[2] + "/" + dateAndTime[1] + "/" + dateAndTime[0] + " ";
        String time = dateAndTime[3];
        return date + time;
    }
     */

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(CS2030);
        assertEquals(CS2030, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(INVALID_NAME, VALID_STATUS, VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }


    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_STATUS, VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    /*
    @Test
    public void toModelType_invalidTimeStart_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, INVALID_TIME,
                        VALID_TIME_END, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS, VALID_PERSONS);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
     */

    /*
    @Test
    public void toModelType_nullTimeStart_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, null,
                VALID_TIME_END, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
     */

    /*
    @Test
    public void toModelType_invalidTimeEnd_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_TIME_START,
                INVALID_TIME, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS, VALID_PERSONS);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
     */

    /*
    @Test
    public void toModelType_nullTimeEnd_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_TIME_START,
                null, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
     */

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, INVALID_STATUS, VALID_PRIORITY, VALID_DESCRIPTION);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_STATUS, null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventPriority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_STATUS, INVALID_PRIORITY, VALID_DESCRIPTION);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_STATUS, null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventPriority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_STATUS, VALID_PRIORITY, INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_STATUS, VALID_PRIORITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    /* commented out for v1.2
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_TIME_START,
                VALID_TIME_END, VALID_STATUS, INVALID_DESCRIPTION, invalidTags, VALID_PERSONS);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_invalidPersons_throwsIllegalValueException() {
        List<JsonAdaptedPerson> invalidPersons = new ArrayList<>(VALID_PERSONS);
        invalidPersons.add(new JsonAdaptedPerson(INVALID_PERSON_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS));
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_TIME_START,
                VALID_TIME_END, VALID_STATUS, INVALID_DESCRIPTION, VALID_TAGS, invalidPersons);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
     */

}
