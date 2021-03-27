package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.EXERCISE;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class PinnedStatusTest {

    @Test
    public void isValidPinnedStatus() {

        // invalid pinnedStatus
        assertFalse(PinnedStatus.isValidStatus("")); // empty string

        // valid pinnedStatus
        assertTrue(PinnedStatus.isValidStatus("PINNED"));
        assertTrue(PinnedStatus.isValidStatus("UNPINNED"));
    }

    @Test
    @Disabled
    //disabled until model support for pinned status
    public void compareTest() {
        assertEquals(ASSIGNMENT.getCompletionStatus().compareTo(EXERCISE.getCompletionStatus()), 0);

        Task assignment = new TaskBuilder(ASSIGNMENT).build();
        assignment.markTaskAsDone();
        assertEquals(assignment.getCompletionStatus().compareTo(EXERCISE.getCompletionStatus()), 1);
        assertEquals(EXERCISE.getCompletionStatus().compareTo(assignment.getCompletionStatus()), -1);
    }
}
