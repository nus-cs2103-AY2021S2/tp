package seedu.address.model.task.completable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
}
