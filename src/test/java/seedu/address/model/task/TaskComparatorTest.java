package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.EXERCISE;
import static seedu.address.testutil.TypicalTasks.LAB;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.InvalidTaskComparatorVariableException;

public class TaskComparatorTest {
    private static final String INVALID_INPUT = "*%&!#*)";
    private static final ArrayList<String> CORRECT_INPUT =
            new ArrayList<>(Arrays.asList("name", "deadline", "priority", "completion"));

    private final TaskComparator taskComparator = new TaskComparator();

    @Test
    public void isValidComparingVar() {
        // null comparingVar
        assertThrows(NullPointerException.class, () -> TaskComparator.isValidComparingVar(null));

        // invalid comparingVar
        assertFalse(TaskComparator.isValidComparingVar(""));
        assertFalse(TaskComparator.isValidComparingVar(INVALID_INPUT));

        // valid comparingVar
        for (String input : CORRECT_INPUT) {
            assertTrue(TaskComparator.isValidComparingVar(input));
        }
    }

    @Test
    public void setComparingVar_invalidInput_throwsInvalidTaskComparatorVariableException() {
        assertThrows(InvalidTaskComparatorVariableException.class, () -> taskComparator.setComparingVar(INVALID_INPUT));
    }

    @Test
    public void compare_twoInvalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskComparator.compare(null, null));
    }

    @Test
    public void compare_oneInvalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskComparator.compare(ASSIGNMENT, null));
        assertThrows(NullPointerException.class, () -> taskComparator.compare(null, EXERCISE));
    }

    @Test
    public void compare_twoValidInputUsingName() {
        taskComparator.setComparingVar("name");
        assertEquals(ASSIGNMENT.getName().compareTo(EXERCISE.getName()),
                taskComparator.compare(ASSIGNMENT, EXERCISE));
        assertEquals(EXERCISE.getName().compareTo(LAB.getName()),
                taskComparator.compare(EXERCISE, LAB));
    }

    @Test
    public void compare_twoValidInputUsingPriority() {
        taskComparator.setComparingVar("priority");
        assertEquals(EXERCISE.getPriority().compareTo(ASSIGNMENT.getPriority()),
                taskComparator.compare(ASSIGNMENT, EXERCISE));
        assertEquals(EXERCISE.getPriority().compareTo(LAB.getPriority()),
                taskComparator.compare(LAB, EXERCISE));
    }

    @Test
    public void compare_twoValidInputUsingCompletionStatus() {
        taskComparator.setComparingVar("completion");
        assertEquals(ASSIGNMENT.getCompletionStatus().compareTo(EXERCISE.getCompletionStatus()),
                taskComparator.compare(ASSIGNMENT, EXERCISE));
        assertEquals(EXERCISE.getCompletionStatus().compareTo(LAB.getCompletionStatus()),
                taskComparator.compare(EXERCISE, LAB));
    }

    @Test
    public void compare_twoValidInputUsingDeadline() {
        taskComparator.setComparingVar("deadline");
        assertEquals(ASSIGNMENT.getDeadline().compareTo(EXERCISE.getDeadline()),
                taskComparator.compare(ASSIGNMENT, EXERCISE));
        assertEquals(EXERCISE.getDeadline().compareTo(LAB.getDeadline()),
                taskComparator.compare(EXERCISE, LAB));
    }
}
