package seedu.address.model.task.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.DeadlineBuilder;
import seedu.address.testutil.TodoBuilder;

public class TodoTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Todo(null));
        assertThrows(NullPointerException.class, () -> new Todo(null, null));
        assertThrows(NullPointerException.class, () -> new Todo("test", null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Todo(invalidDescription));
        assertThrows(IllegalArgumentException.class, () -> new Todo(invalidDescription, false));
        String invalidDescription2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Todo(invalidDescription2));
        assertThrows(IllegalArgumentException.class, () -> new Todo(invalidDescription2, false));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Todo.isValidDescription(null));

        // invalid description
        assertFalse(Todo.isValidDescription("")); // empty string
        assertFalse(Todo.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Todo.isValidDescription("Blk 456, Den Road, #01-355"));
        assertTrue(Todo.isValidDescription("-")); // one character
        assertTrue(Todo.isValidDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }

    @Test
    public void equals() {
        Todo testTodo = new Todo("test todo");
        Todo differentTodo = new Todo("different todo");
        // same values -> returns true
        Todo todoCopy = new TodoBuilder(testTodo).build();
        assertEquals(testTodo, todoCopy);

        // same object -> returns true
        assertEquals(testTodo, testTodo);

        // null -> returns false
        assertNotEquals(testTodo, null);

        // different type -> returns false
        assertNotEquals(testTodo, 5);

        // different event -> returns false
        assertNotEquals(differentTodo, testTodo);

        // different name -> returns false
        Todo editedTodo = new TodoBuilder().withDescription("editted todo").build();
        assertNotEquals(editedTodo, testTodo);

    }

    @Test
    public void setDescription_success() {
        Todo todo = new TodoBuilder().withDescription("todo").build();
        assertEquals("todo", todo.getDescription());
        String description = "this is a test description.";
        todo.setDescription(description);
        assertEquals(description, todo.getDescription());
    }
}
