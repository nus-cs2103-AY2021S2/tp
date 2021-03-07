package seedu.module.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.module.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.model.task.Description;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Deadline;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_MODULE = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final String VALID_MODULE = BENSON.getModule().toString();
    private static final String VALID_ADDRESS = BENSON.getDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BENSON);
        assertEquals(BENSON, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_DEADLINE, VALID_MODULE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE, VALID_MODULE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_DEADLINE, VALID_MODULE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_MODULE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, INVALID_MODULE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_MODULE, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_MODULE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_MODULE, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
