package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.RETURNBOOK;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskContainsKeywordPredicate;
import seedu.address.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTasksCommand}.
 */
public class FindTasksCommandTest {
    private Model model = new ModelManager(TypicalTasks.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordPredicate firstPredicate =
                new TaskContainsKeywordPredicate(Collections.singletonList("first"));
        TaskContainsKeywordPredicate secondPredicate =
                new TaskContainsKeywordPredicate(Collections.singletonList("second"));

        FindTasksCommand findFirstCommand = new FindTasksCommand(firstPredicate);
        FindTasksCommand findSecondCommand = new FindTasksCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTasksCommand findFirstCommandCopy = new FindTasksCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordPredicate predicate = preparePredicate(" ");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordPredicate predicate = preparePredicate("Homework national Library");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOMEWORK, RETURNBOOK), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskContainsKeywordPredicate}.
     */
    private TaskContainsKeywordPredicate preparePredicate(String userInput) {
        return new TaskContainsKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
