package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysCompletableTodo;

import org.junit.jupiter.api.Test;

import guitests.guihandles.TodoCardHandle;
import seedu.address.model.task.CompletableTodo;
import seedu.address.testutil.TodoBuilder;

/**
 * Contains tests for the {@code TodoCard}.
 */
public class TodoCardTest extends GuiUnitTest {

    private static final boolean DONE = true;
    private static final boolean NOT_DONE = false;

    @Test
    public void display_success() {
        // todo is done
        CompletableTodo todoIsDone = new TodoBuilder().withDescription("Display Test").withIsDone(DONE).build();
        TodoCard todoCard = new TodoCard(todoIsDone, 1);
        uiPartExtension.setUiPart(todoCard);
        assertCardDisplay(todoCard, todoIsDone, 1);

        // todo is not done
        CompletableTodo todoIsNotDone = new TodoBuilder().withDescription("Display Test")
                .withIsDone(NOT_DONE).build();
        todoCard = new TodoCard(todoIsNotDone, 2);
        uiPartExtension.setUiPart(todoCard);
        assertCardDisplay(todoCard, todoIsNotDone, 2);
    }

    @Test
    public void equals() {
        CompletableTodo todo = new TodoBuilder().build();
        TodoCard todoCard = new TodoCard(todo, 0);

        // same todo, same index -> returns true
        TodoCard copy = new TodoCard(todo, 0);
        assertTrue(todoCard.equals(copy));

        // same object -> returns true
        assertTrue(todoCard.equals(todoCard));

        // null -> returns false
        assertFalse(todoCard.equals(null));

        // different types -> returns false
        assertFalse(todoCard.equals(0));

        // different todo, same index -> returns false
        CompletableTodo differentTodo = new TodoBuilder().withDescription("differentDescription").build();
        assertFalse(todoCard.equals(new TodoCard(differentTodo, 0)));

        // same todo, different index -> returns false
        assertFalse(todoCard.equals(new TodoCard(todo, 1)));
    }

    /**
     * Asserts that {@code todoCard} displays the details of {@code expectedTodo}
     * correctly and matches {@code expectedId}.
     */
    private void assertCardDisplay(
            TodoCard todoCard, CompletableTodo expectedTodo, int expectedId) {
        guiRobot.pauseForHuman();

        TodoCardHandle todoCardHandle =
                new TodoCardHandle(todoCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", todoCardHandle.getId());

        // verify project details are displayed correctly
        assertCardDisplaysCompletableTodo(expectedTodo, todoCardHandle);
    }
}
