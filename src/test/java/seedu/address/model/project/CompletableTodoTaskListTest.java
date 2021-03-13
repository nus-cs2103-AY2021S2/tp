package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;

public class CompletableTodoTaskListTest {

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
        CompletableTodo completableTodo = new Todo("Test Description");
        ArrayList<CompletableTodo> completableTodos = new ArrayList<>();
        completableTodos.add(completableTodo);
        assertDoesNotThrow(() -> new CompletableTaskList(completableTodos));
    }

    @Test
    public void getCompletableTasks_validCompletableTaskList_equalsOriginalList() {
        CompletableTodo completableTodo = new Todo("Test Description");
        ArrayList<CompletableTodo> completableTodos = new ArrayList<>();
        completableTodos.add(completableTodo);
        CompletableTaskList completableTaskList = new CompletableTaskList(completableTodos);
        assertEquals(completableTodos, completableTaskList.getCompletableTasks());
    }
}
