package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskStatusTest {

    @Test
    public void null_enum_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TaskStatus.valueOf(null));
    }

    @Test
    public void invalid_enum_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> TaskStatus.valueOf(""));

        assertThrows(IllegalArgumentException.class, () -> TaskStatus.valueOf("completed"));
        assertThrows(IllegalArgumentException.class, () -> TaskStatus.valueOf("uncompleted"));

        assertThrows(IllegalArgumentException.class, () -> TaskStatus.valueOf("Completed"));
        assertThrows(IllegalArgumentException.class, () -> TaskStatus.valueOf("Uncompleted"));
    }

    @Test
    public void isValidTaskStatusValue() {
        // invalid TaskStatus
        assertFalse(TaskStatus.isValidValue(null));
        assertFalse(TaskStatus.isValidValue("")); // empty string
        assertFalse(TaskStatus.isValidValue("In Progress")); // No such value

        assertFalse(TaskStatus.isValidValue("Completed"));
        assertFalse(TaskStatus.isValidValue("Uncompleted"));

        assertFalse(TaskStatus.isValidValue("COMPLETED"));
        assertFalse(TaskStatus.isValidValue("UNCOMPLETED"));

        // valid description
        assertTrue(TaskStatus.isValidValue("completed")); // completed value
        assertTrue(TaskStatus.isValidValue("uncompleted")); // uncompleted value
    }

    @Test
    public void getStatus() {
        assertEquals(TaskStatus.valueOf("COMPLETED").getStatus(), "completed");
        assertNotEquals(TaskStatus.valueOf("COMPLETED").getStatus(), "Completed");
        assertNotEquals(TaskStatus.valueOf("COMPLETED").getStatus(), "COMPLETED");

        assertEquals(TaskStatus.valueOf("UNCOMPLETED").getStatus(), "uncompleted");
        assertNotEquals(TaskStatus.valueOf("UNCOMPLETED").getStatus(), "Uncompleted");
        assertNotEquals(TaskStatus.valueOf("UNCOMPLETED").getStatus(), "UNCOMPLETED");
    }

    @Test
    public void printTaskStatusResult() {
        assertEquals(TaskStatus.valueOf("COMPLETED").toString(), "completed");
        assertEquals(TaskStatus.valueOf("UNCOMPLETED").toString(), "uncompleted");

        assertNotEquals(TaskStatus.valueOf("COMPLETED").toString(), "COMPLETED");
        assertNotEquals(TaskStatus.valueOf("UNCOMPLETED").toString(), "UNCOMPLETED");

        assertNotEquals(TaskStatus.valueOf("COMPLETED").toString(), "Completed");
        assertNotEquals(TaskStatus.valueOf("UNCOMPLETED").toString(), "Uncompleted");
    }

    @Test
    public void getEnumName() {
        assertEquals(TaskStatus.valueOf("COMPLETED").getEnumName(), "Task Status");
        assertNotEquals(TaskStatus.valueOf("COMPLETED").getEnumName(), "TaskStatus");
        assertNotEquals(TaskStatus.valueOf("COMPLETED").getEnumName(), "taskStatus");
        assertNotEquals(TaskStatus.valueOf("COMPLETED").getEnumName(), "taskstatus");

    }

}
