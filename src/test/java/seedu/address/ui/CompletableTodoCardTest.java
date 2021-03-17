package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysCompletableTodo;

import org.junit.jupiter.api.Test;

import guitests.guihandles.CompletableTodoCardHandle;
import seedu.address.model.task.CompletableTodo;
import seedu.address.testutil.TodoBuilder;

/**
 * Contains tests for the {@code CompletableTodoCard}.
 */
public class CompletableTodoCardTest extends GuiUnitTest {

    private static final boolean DONE = true;
    private static final boolean NOT_DONE = false;

    @Test
    public void display() {
        // todo is done
        CompletableTodo todoIsDone = new TodoBuilder().withDescription("Display Test").withIsDone(DONE).build();
        CompletableTodoCard todoCard = new CompletableTodoCard(todoIsDone, 1);
        uiPartExtension.setUiPart(todoCard);
        assertCardDisplay(todoCard, todoIsDone, 1);

        // todo is not done
        CompletableTodo todoIsNotDone = new TodoBuilder().withDescription("Display Test")
                .withIsDone(NOT_DONE).build();
        todoCard = new CompletableTodoCard(todoIsNotDone, 2);
        uiPartExtension.setUiPart(todoCard);
        assertCardDisplay(todoCard, todoIsNotDone, 2);
    }

    @Test
    public void equals() {
        CompletableTodo todo = new TodoBuilder().build();
        CompletableTodoCard todoCard = new CompletableTodoCard(todo, 0);

        // same todo, same index -> returns true
        CompletableTodoCard copy = new CompletableTodoCard(todo, 0);
        assertTrue(todoCard.equals(copy));

        // same object -> returns true
        assertTrue(todoCard.equals(todoCard));

        // null -> returns false
        assertFalse(todoCard.equals(null));

        // different types -> returns false
        assertFalse(todoCard.equals(0));

        // different todo, same index -> returns false
        CompletableTodo differentTodo = new TodoBuilder().withDescription("differentDescription").build();
        assertFalse(todoCard.equals(new CompletableTodoCard(differentTodo, 0)));

        // same todo, different index -> returns false
        assertFalse(todoCard.equals(new CompletableTodoCard(todo, 1)));
    }

    /**
     * Asserts that {@code completedTodoCard} displays the details of {@code completedTodo}
     * correctly and matches {@code expectedId}.
     */
    private void assertCardDisplay(
            CompletableTodoCard todoCard, CompletableTodo expectedTodo, int expectedId) {
        guiRobot.pauseForHuman();

        CompletableTodoCardHandle completableTodoCardHandle =
                new CompletableTodoCardHandle(todoCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", completableTodoCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysCompletableTodo(expectedTodo, completableTodoCardHandle);
    }
}
