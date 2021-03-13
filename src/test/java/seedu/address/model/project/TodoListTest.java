package seedu.address.model.project;

import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class TodoListTest {

    @Test
    public void constructor_empty_createEmptyTodoList() {
        TodoList emptyTodoList = new TodoList();
        assertTrue(emptyTodoList.getTodos().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TodoList(null));
    }

    @Test
    public void constructor_singleTodo_valid() {
        Todo todo = new Todo("Test Description");
        ArrayList<CompletableTodo> todos = new ArrayList<>();
        todos.add(todo);
        assertDoesNotThrow(() -> new TodoList(todos));
    }

    @Test
    public void getCompletableTasks_validCompletableTaskList_equalsOriginalList() {
        Todo todo = new Todo("Test Description");
        ArrayList<CompletableTodo> todos = new ArrayList<>();
        todos.add(todo);
        TodoList todoList = new TodoList(todos);
        assertEquals(todos, todoList.getTodos());
    }
}
