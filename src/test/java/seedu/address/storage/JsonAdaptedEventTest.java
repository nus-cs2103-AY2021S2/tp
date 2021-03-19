package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.event.Time;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = ")($&890";
    private static final String INVALID_START_DATE = ")($&890";
    private static final String INVALID_START_TIME = ")($&890";
    private static final String INVALID_END_DATE = ")($&890";
    private static final String INVALID_END_TIME = ")($&890";
    private static final String INVALID_TAGS = ")($&890";
    private static final String INVALID_CATEGORIES = ")($&890";

    private static final String VALID_NAME = MEETING.getName().toString();
    private static final String VALID_START_DATE = MEETING.getStartDate().toString();
    private static final String VALID_START_TIME = MEETING.getStartTime().toString();
    private static final String VALID_END_DATE = MEETING.getEndDate().toString();
    private static final String VALID_END_TIME = MEETING.getEndTime().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = MEETING.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = MEETING.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsTask() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(MEETING);
        assertEquals(MEETING, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_START_DATE, VALID_START_TIME,
                        VALID_END_DATE, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_START_DATE, VALID_START_TIME,
                        VALID_END_DATE, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_START_DATE, VALID_START_TIME,
                        VALID_END_DATE, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, null, VALID_START_TIME,
                        VALID_END_DATE, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_DATE, INVALID_START_TIME,
                        VALID_END_DATE, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_DATE, VALID_START_TIME,
                        INVALID_END_DATE, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_DATE, VALID_START_TIME,
                        null, VALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_DATE, VALID_START_TIME,
                        VALID_END_DATE, INVALID_END_TIME, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAGS));
        JsonAdaptedEvent task =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_DATE, VALID_START_TIME,
                        VALID_END_DATE, INVALID_END_TIME, VALID_CATEGORIES, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORIES));
        JsonAdaptedEvent task =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_DATE, VALID_START_TIME,
                        VALID_END_DATE, INVALID_END_TIME, invalidCategories, VALID_TAGS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
