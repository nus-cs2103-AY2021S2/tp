package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;
import seedu.address.testutil.TodoBuilder;

public class JsonAdaptedTodoTest {
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_DESCRIPTION = TodoBuilder.DEFAULT_DESCRIPTION;
    private static final Boolean VALID_IS_DONE = TodoBuilder.DEFAULT_IS_DONE;

    private static final Todo ASSIGNMENT = new TodoBuilder()
            .withDescription(VALID_DESCRIPTION).withIsDone(VALID_IS_DONE).build();

    @Test
    public void toModelType_validTodoDetails_returnsTodo() throws Exception {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(ASSIGNMENT);
        assertEquals(ASSIGNMENT, todo.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalArgumentException() {
        JsonAdaptedTodo todo =
                new JsonAdaptedTodo(INVALID_DESCRIPTION, VALID_IS_DONE);
        String expectedMessage = CompletableTodo.MESSAGE_CONSTRAINTS_DESCRIPTION;
        assertThrows(IllegalArgumentException.class, expectedMessage, todo::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsNullPointerException() {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(null, VALID_IS_DONE);
        assertThrows(NullPointerException.class, todo::toModelType);
    }
}
