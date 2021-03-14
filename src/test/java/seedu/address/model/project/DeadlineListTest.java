package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.Deadline;

public class DeadlineListTest {

    @Test
    public void constructor_empty_createEmptyDeadlineList() {
        DeadlineList emptyDeadlineList = new DeadlineList();
        assertTrue(emptyDeadlineList.getDeadlines().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineList(null));
    }

    @Test
    public void constructor_singleCompletableTask_valid() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        Deadline deadline = new Deadline("Test Description", validDate);
        ArrayList<CompletableDeadline> deadlines = new ArrayList<>();
        deadlines.add(deadline);
        assertDoesNotThrow(() -> new DeadlineList(deadlines));
    }

    @Test
    public void getCompletableTasks_validCompletableTaskList_equalsOriginalList() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        Deadline deadline = new Deadline("Test Description", validDate);
        ArrayList<CompletableDeadline> deadlines = new ArrayList<>();
        deadlines.add(deadline);
        DeadlineList deadlineList = new DeadlineList(deadlines);
        assertEquals(deadlines, deadlineList.getDeadlines());
    }

    @Test
    public void getCopyOf_validDeadlineList_copyOfOriginal() {
        DeadlineList deadlineList = new DeadlineList();
        DeadlineList deadlineListCopy = deadlineList.getCopy();
        assertEquals(deadlineList, deadlineListCopy);
        assertFalse(deadlineList == deadlineListCopy);
    }
}
