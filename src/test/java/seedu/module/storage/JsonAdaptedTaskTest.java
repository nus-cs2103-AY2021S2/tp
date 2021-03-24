package seedu.module.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.module.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.MIDTERM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.model.task.Time;
import seedu.module.model.task.Description;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Workload;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "+651234";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_DONE_STATUS = "not boolean string";
    private static final String INVALID_MODULE = "example.com";
    private static final String INVALID_WORKLOAD = "4";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = MIDTERM.getName().toString();
    private static final String VALID_START_TIME = MIDTERM.getStartTime().toString();
    private static final String VALID_DEADLINE = MIDTERM.getDeadline().toString();
    private static final String VALID_MODULE = MIDTERM.getModule().toString();
    private static final String VALID_DESCRIPTION = MIDTERM.getDescription().toString();
    private static final String VALID_WORKLOAD = Integer.toString(MIDTERM.getWorkload().workloadLevel);
    private static final String VALID_DONE_STATUS = MIDTERM.getDoneStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = MIDTERM.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(MIDTERM);
        assertEquals(MIDTERM, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                        VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, INVALID_DEADLINE, VALID_MODULE,
                        VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, null, VALID_MODULE,
                VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, INVALID_MODULE,
                        VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, null,
                VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                        INVALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                null, VALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDoneStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                VALID_DESCRIPTION, VALID_WORKLOAD, INVALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = DoneStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDoneStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                VALID_DESCRIPTION, VALID_WORKLOAD, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DoneStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidWorkload_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                VALID_DESCRIPTION, INVALID_WORKLOAD, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = Workload.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullWorkload_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                VALID_DESCRIPTION, null, VALID_DONE_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Workload.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_START_TIME, VALID_DEADLINE, VALID_MODULE,
                        VALID_DESCRIPTION, VALID_WORKLOAD, VALID_DONE_STATUS, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
