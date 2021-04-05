package seedu.heymatez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.heymatez.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.heymatez.testutil.Assert.assertThrows;
import static seedu.heymatez.testutil.TypicalTasks.HOMEWORK;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.exceptions.IllegalValueException;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.TaskStatus;
import seedu.heymatez.model.task.Title;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TITLE = "";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_DEADLINE = "4 March 2021";
    private static final String INVALID_STATUS = "Completed";
    private static final String INVALID_PRIORITY = "High";
    private static final String INVALID_ASSIGNEE = "R@chel";

    private static final String VALID_TITLE = HOMEWORK.getTitle().taskTitle;
    private static final String VALID_DESCRIPTION = HOMEWORK.getDescription().desc;
    private static final String VALID_DEADLINE = HOMEWORK.getDeadline().unformattedDate;
    private static final String VALID_STATUS = HOMEWORK.getTaskStatus().status;
    private static final String VALID_PRIORITY = HOMEWORK.getPriority().priority;
    private static final List<JsonAdaptedAssignee> VALID_ASSIGNEE = HOMEWORK.getAssignees().stream()
            .map(JsonAdaptedAssignee::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(HOMEWORK);
        assertEquals(HOMEWORK, task.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_STATUS,
                        VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DESCRIPTION, VALID_DEADLINE, VALID_STATUS,
                VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, INVALID_DESCRIPTION, VALID_DEADLINE, VALID_STATUS,
                        VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, null, VALID_DEADLINE, VALID_STATUS,
                VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }


    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, INVALID_DEADLINE, VALID_STATUS,
                        VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, null, VALID_STATUS,
                VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, INVALID_STATUS,
                        VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = TaskStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, null,
                VALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskStatus.getEnumName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_STATUS,
                        INVALID_PRIORITY, VALID_ASSIGNEE);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_STATUS,
                null, VALID_ASSIGNEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.getEnumName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidAssignees_throwsIllegalValueException() {
        List<JsonAdaptedAssignee> invalidAssignees = new ArrayList<>(VALID_ASSIGNEE);
        invalidAssignees.add(new JsonAdaptedAssignee(INVALID_ASSIGNEE));

        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_STATUS,
                        VALID_PRIORITY, invalidAssignees);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
