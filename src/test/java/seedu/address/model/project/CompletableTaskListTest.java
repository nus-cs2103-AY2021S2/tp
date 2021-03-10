package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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

}
