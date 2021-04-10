package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.BENSON;
import static seedu.address.testutil.TypicalTasks.CARL;
import static seedu.address.testutil.TypicalTasks.GEORGE;
import static seedu.address.testutil.TypicalTasks.IDA;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.predicates.TaskOnDatePredicate;

class ViewDayCommandTest {
    private Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPlanner(), new UserPrefs());

    @Test
    public void execute_viewDayWithDateTask_taskFound() {
        String expectedMessage = String.format(ViewDayCommand.MESSAGE_VIEW_DAY_SUCCESS, 1, "December", "2021");
        ViewDayCommand command = buildViewDayCommandWithDate("12/12/2021");
        assertEquals(Arrays.asList(BENSON), expectedModel.getFilteredTaskList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewDayWithRecurringTask_taskFound() {
        String expectedMessage = String.format(ViewDayCommand.MESSAGE_VIEW_DAY_SUCCESS, 1, "June", "2021");
        ViewDayCommand command = buildViewDayCommandWithDate("03/06/2021");
        assertEquals(Arrays.asList(GEORGE), expectedModel.getFilteredTaskList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewDayWithRecurringAndDateTask_multipleTasksFound() {
        model.addTask(IDA);
        expectedModel.addTask(IDA);
        String expectedMessage = String.format(ViewDayCommand.MESSAGE_VIEW_DAY_SUCCESS, 2, "May", "2021");
        ViewDayCommand command = buildViewDayCommandWithDate("05/05/2021");
        assertEquals(Arrays.asList(CARL, IDA), expectedModel.getFilteredTaskList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_viewDayWithoutTasks_noTasksFound() {
        String expectedMessage = String.format(ViewDayCommand.MESSAGE_VIEW_DAY_SUCCESS, 0, "April", "2021");
        ViewDayCommand command = buildViewDayCommandWithDate("24/04/2021");
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Builds a TaskOnDatePredicate with the given string date, and then builds the ViewDayCommand with that predicate.
     * Updates expectedModel's filtered task list.
     *
     * @param date Date to filter the task list with.
     * @return ViewDayCommand formed with the date given.
     */
    private ViewDayCommand buildViewDayCommandWithDate(String date) {
        Date predicateDate = new Date(date);
        TaskOnDatePredicate predicate = new TaskOnDatePredicate(predicateDate);
        ViewDayCommand command = new ViewDayCommand(predicate, predicateDate.getDate());
        expectedModel.updateFilteredTaskList(predicate);
        return command;
    }
}
