package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Status;
import seedu.address.model.task.Title;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TITLE = "R@chel";
    private static final String INVALID_DEADLINE = " ";
    private static final String INVALID_STARTTIME = " ";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_RECURRINGSCHEDULE = "[08@06*2021][Tue][lyweek]";
    private static final String INVALID_STATUS = "done1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TITLE = BENSON.getTitle().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final String VALID_STARTTIME = BENSON.getStartTime().toString();
    private static final String VALID_RECURRINGSCHEDULE = BENSON.getRecurringSchedule().value;
    private static final String VALID_DESCRIPTION = BENSON.getDescription().toString();
    private static final String VALID_STATUS = BENSON.getStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BENSON);
        assertEquals(BENSON, task.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TITLE, VALID_DEADLINE, VALID_STARTTIME, VALID_RECURRINGSCHEDULE,
                        VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE, VALID_STARTTIME, VALID_RECURRINGSCHEDULE,
                VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, INVALID_DEADLINE, VALID_STARTTIME, VALID_RECURRINGSCHEDULE,
                        VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, null, VALID_STARTTIME, VALID_RECURRINGSCHEDULE,
                VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, INVALID_STARTTIME, VALID_RECURRINGSCHEDULE,
                        VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = StartTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, null, VALID_RECURRINGSCHEDULE,
                VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidRecurringSchedule_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, VALID_STARTTIME, INVALID_RECURRINGSCHEDULE,
                        VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = RecurringSchedule.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullRecurringSchedule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, VALID_STARTTIME, null,
                VALID_DESCRIPTION, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RecurringSchedule.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, VALID_STARTTIME,
                VALID_RECURRINGSCHEDULE, null, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, VALID_STARTTIME,
                VALID_RECURRINGSCHEDULE, VALID_DESCRIPTION, INVALID_STATUS, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DEADLINE, VALID_STARTTIME, VALID_RECURRINGSCHEDULE,
                        VALID_DESCRIPTION, VALID_STATUS, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
