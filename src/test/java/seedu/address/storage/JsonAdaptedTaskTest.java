package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Priority;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = ",!@#%$";
    private static final String INVALID_DEADLINE = ",!@#%$";
    private static final String INVALID_PRIORITY = ",!@#%$";
    private static final String INVALID_COMPLETION = ",!@#%$";
    private static final String INVALID_TAGS = ",!@#%$";
    private static final String INVALID_CATEGORIES = ",!@#%$";

    private static final String VALID_NAME = ASSIGNMENT.getName().toString();
    private static final String VALID_DEADLINE = ASSIGNMENT.getDeadline().toString();
    private static final String VALID_PRIORITY = ASSIGNMENT.getPriority().toString();
    private static final String VALID_COMPLETION = ASSIGNMENT.getCompletionStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ASSIGNMENT.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = ASSIGNMENT.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(ASSIGNMENT);
        assertEquals(ASSIGNMENT, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(INVALID_NAME, VALID_DEADLINE, VALID_PRIORITY,
                        VALID_COMPLETION, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(null, VALID_DEADLINE, VALID_PRIORITY,
                        VALID_COMPLETION, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, INVALID_DEADLINE, VALID_PRIORITY,
                        VALID_COMPLETION, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, null, VALID_PRIORITY,
                        VALID_COMPLETION, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, INVALID_PRIORITY,
                        VALID_COMPLETION, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCompletion_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_PRIORITY,
                        INVALID_COMPLETION, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = CompletionStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCompletion_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_PRIORITY,
                        null, VALID_CATEGORIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompletionStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAGS));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_PRIORITY,
                        VALID_COMPLETION, VALID_CATEGORIES, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORIES));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_PRIORITY,
                        VALID_COMPLETION, invalidCategories, VALID_TAGS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
