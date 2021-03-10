package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Completable;
import seedu.address.model.task.completable.Todo;

public class CompletableTaskListTest {

    @Test
    public void constructor_empty_createEmptyCompletableTaskList() {
        CompletableTaskList emptyCompletableTaskList = new CompletableTaskList();
        assertTrue(emptyCompletableTaskList.getCompletableTasks().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompletableTaskList(null));
    }

    @Test
    public void constructor_singleCompletableTask_valid() {
        Completable completable = new Todo("Test Description");
        ArrayList<Completable> completables = new ArrayList<>();
        completables.add(completable);
        assertDoesNotThrow(() -> new CompletableTaskList(completables));
    }
}
