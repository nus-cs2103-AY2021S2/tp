package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;

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
