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
        String invalidTaskStatus = "";
        assertThrows(IllegalArgumentException.class, () -> TaskStatus.valueOf(invalidTaskStatus));
    }

    @Test
    public void isValidTaskStatusValue() {
        // invalid TaskStatus
        assertFalse(TaskStatus.isValidValue(null));
        assertFalse(TaskStatus.isValidValue("")); // empty string
        assertFalse(TaskStatus.isValidValue("In Progress")); // No such value


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
        assertEquals(TaskStatus.valueOf("COMPLETED").toString(), "Completed");
        assertEquals(TaskStatus.valueOf("UNCOMPLETED").toString(), "Uncompleted");
    }

    @Test
    public void getEnumName() {
        assertEquals(TaskStatus.valueOf("COMPLETED").getEnumName(), "Task Status");
    }

}
