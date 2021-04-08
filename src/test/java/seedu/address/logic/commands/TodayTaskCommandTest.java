package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.DUE;
import static seedu.address.testutil.TypicalTasks.getTypicalSochedule;
import static seedu.address.testutil.TypicalTasks.getTypicalSocheduleWithTodayTask;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskDeadlineIsTodayPredicate;

public class TodayTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSochedule(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSochedule(), new UserPrefs());
    }

    @Test
    public void execute_noDeadlineToday_noTaskFound() {
        String expectedMessage = String.format(TodayTaskCommand.MESSAGE_TODAY_TASK_SUCCESS
                + MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskDeadlineIsTodayPredicate predicate = new TaskDeadlineIsTodayPredicate();
        TodayTaskCommand command = new TodayTaskCommand();
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleDeadlineToday_multipleTasksFound() {
        String expectedMessage = String.format(TodayTaskCommand.MESSAGE_TODAY_TASK_SUCCESS
                + MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        TaskDeadlineIsTodayPredicate predicate = new TaskDeadlineIsTodayPredicate();
        TodayTaskCommand command = new TodayTaskCommand();
        updateModelWithTodayTask();
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DUE), model.getFilteredTaskList());
    }

    private void updateModelWithTodayTask() {
        model = new ModelManager(getTypicalSocheduleWithTodayTask(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSocheduleWithTodayTask(), new UserPrefs());
    }
}
