package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.MARATHON;
import static seedu.address.testutil.TypicalTasks.RETURNBOOK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DeadlineBeforeDatePredicate;


public class FindTasksBeforeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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

}
