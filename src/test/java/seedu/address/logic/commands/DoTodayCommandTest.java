package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.util.OperationFlag;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskTracker;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Task;

public class DoTodayCommandTest {

    private OperationFlag addOperationFlag = new OperationFlag(OperationFlag.ADD_FLAG);

    private OperationFlag removeOperationFlag = new OperationFlag(OperationFlag.REMOVE_FLAG);

    private Model modelNoDailyTasks = new ModelManager(getTypicalTaskTracker(), new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoTodayCommand(null, null));
    }

    @Test
    public void execute_validAllTaskIndex_addSuccessful() {
        Task dailyTaskToAdd = modelNoDailyTasks.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoTodayCommand doTodayCommand = new DoTodayCommand(INDEX_FIRST_TASK, addOperationFlag);

        String expectedMessage = String.format(DoTodayCommand.MESSAGE_ADDED_TASK_SUCCESS, dailyTaskToAdd);

        Model expectedModel = new ModelManager(new TaskTracker(modelNoDailyTasks.getTaskTracker()), new UserPrefs());
        expectedModel.addToDailyToDoList(dailyTaskToAdd);

        assertCommandSuccess(doTodayCommand, modelNoDailyTasks, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDailyTaskIndex_removeSuccessful() {
        Model modelWithDailyTask = getModelWithDailyTask();
        Task dailyTaskToRemove = modelWithDailyTask.getDailyTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoTodayCommand doTodayCommand = new DoTodayCommand(INDEX_FIRST_TASK, removeOperationFlag);

        String expectedMessage = String.format(DoTodayCommand.MESSAGE_REMOVED_TASK_SUCCESS, dailyTaskToRemove);

        Model expectedModel = getModelWithDailyTask();
        expectedModel.removeFromDailyToDoList(dailyTaskToRemove);

        assertCommandSuccess(doTodayCommand, modelWithDailyTask, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAllTaskIndexAddDailyTask_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelNoDailyTasks.getFilteredTaskList().size() + 1);
        DoTodayCommand doTodayCommand = new DoTodayCommand(outOfBoundIndex, addOperationFlag);

        assertCommandFailure(doTodayCommand, modelNoDailyTasks, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDailyTaskIndexRemoveDailyTask_throwsCommandException() {
        Model modelWithDailyTask = getModelWithDailyTask();
        Index outOfBoundIndex = Index.fromOneBased(modelWithDailyTask.getDailyTaskList().size() + 1);
        DoTodayCommand doTodayCommand = new DoTodayCommand(outOfBoundIndex, removeOperationFlag);

        assertCommandFailure(doTodayCommand, modelWithDailyTask, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    private Model getModelWithDailyTask() {
        Task dailyTaskToAdd = modelNoDailyTasks.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Model modelWithDailyTask = new ModelManager(new TaskTracker(modelNoDailyTasks.getTaskTracker()),
                new UserPrefs());
        modelWithDailyTask.addToDailyToDoList(dailyTaskToAdd);

        return modelWithDailyTask;
    }
}
