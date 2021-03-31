package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertTrue(emptyTodoList.getSortedTodos().isEmpty());
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
        assertEquals(todos, todoList.getSortedTodos());
    }

    @Test
    public void getCopyOf_validTodoList_copyOfOriginal() {
        TodoList todoList = new TodoList();
        TodoList todoListCopy = todoList.getCopy();
        assertEquals(todoList, todoListCopy);
        assertFalse(todoList == todoListCopy);
    }

    @Test
    public void deleteTodo_success() {
        Todo todo = new Todo("Test Description");
        ArrayList<CompletableTodo> todos = new ArrayList<>();
        todos.add(todo);
        TodoList todoList = new TodoList(todos);
        assertEquals(todoList.getSortedTodos().size(), 1);
        todoList.deleteTodo(0);
        assertEquals(todoList.getSortedTodos().size(), 0);
    }

    @Test void markAsDone_success() {
        Todo todo = new Todo("Test Description");
        ArrayList<CompletableTodo> todos = new ArrayList<>();
        todos.add(todo);
        TodoList todoList = new TodoList(todos);
        assertEquals(todoList.getSortedTodos().size(), 1);
        assertEquals(todoList.getSortedTodos().get(0).getIsDone(), false);
        todoList.markAsDone(0);
        assertEquals(todoList.getSortedTodos().get(0).getIsDone(), true);
    }

}
