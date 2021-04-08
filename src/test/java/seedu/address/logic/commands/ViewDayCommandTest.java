package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.GEORGE;
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
    public void execute_viewDayWithTasks_multipleTasksFound() {
        String expectedMessage = String.format(ViewDayCommand.MESSAGE_VIEW_DAY_SUCCESS, 1, "June", "2021");
        Date date = new Date("03/06/2021");
        TaskOnDatePredicate predicate = new TaskOnDatePredicate(date);
        ViewDayCommand command = new ViewDayCommand(predicate, date.getDate());
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_viewDayWithoutTasks_noTasksFound() {
        String expectedMessage = String.format(ViewDayCommand.MESSAGE_VIEW_DAY_SUCCESS, 0, "April", "2021");
        Date date = new Date("24/04/2021");
        TaskOnDatePredicate predicate = new TaskOnDatePredicate(date);
        ViewDayCommand command = new ViewDayCommand(predicate, date.getDate());
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }
}
