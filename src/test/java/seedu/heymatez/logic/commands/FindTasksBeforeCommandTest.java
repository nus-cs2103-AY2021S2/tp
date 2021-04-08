package seedu.heymatez.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;
import static seedu.heymatez.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalTasks.HOMEWORK;
import static seedu.heymatez.testutil.TypicalTasks.MARATHON;
import static seedu.heymatez.testutil.TypicalTasks.RETURNBOOK;
import static seedu.heymatez.testutil.TypicalTasks.getTypicalHeyMatez;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.DeadlineBeforeDatePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTasksBeforeCommand}.
 */
public class FindTasksBeforeCommandTest {
    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void equals() {
        DeadlineBeforeDatePredicate firstPredicate =
                new DeadlineBeforeDatePredicate("2021-04-13");
        DeadlineBeforeDatePredicate secondPredicate =
                new DeadlineBeforeDatePredicate("2021-05-06");

        FindTasksBeforeCommand findFirstCommand = new FindTasksBeforeCommand(firstPredicate);
        FindTasksBeforeCommand findSecondCommand = new FindTasksBeforeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTasksBeforeCommand findFirstCommandCopy = new FindTasksBeforeCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different deadline -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_deadline_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        DeadlineBeforeDatePredicate predicate = new DeadlineBeforeDatePredicate("2021-02-03");
        FindTasksBeforeCommand command = new FindTasksBeforeCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_deadline_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        DeadlineBeforeDatePredicate predicate = new DeadlineBeforeDatePredicate("2021-06-05");
        FindTasksBeforeCommand command = new FindTasksBeforeCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOMEWORK, RETURNBOOK, MARATHON), model.getFilteredTaskList());
    }

    @Test
    public void execute_deadline_taskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        DeadlineBeforeDatePredicate predicate = new DeadlineBeforeDatePredicate("2021-02-05");
        FindTasksBeforeCommand command = new FindTasksBeforeCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOMEWORK), model.getFilteredTaskList());
    }

    @Test
    public void execute_emptyFilteredList_success() {
        HeyMatez heyMatez = new HeyMatez();
        model = new ModelManager(heyMatez, new UserPrefs());
        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        DeadlineBeforeDatePredicate predicate = new DeadlineBeforeDatePredicate("2021-02-05");
        FindTasksBeforeCommand findTasksCommand = new FindTasksBeforeCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(findTasksCommand, model, MESSAGE_EMPTY_TASK_LIST, expectedModel);
    }

}
