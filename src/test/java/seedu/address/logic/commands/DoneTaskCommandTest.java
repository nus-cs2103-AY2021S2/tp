package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INDEXES_LIST_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INDEXES_LIST_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_LIST_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_LIST_TWO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalSochedule;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneTaskCommand}.
 */
public class DoneTaskCommandTest {
    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        List<Index> validIndexList = INDEX_LIST_ONE;
        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(validIndexList);

        String expectedMessage = String.format(DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        Task completedTask = new TaskBuilder(taskToDone).buildCompletedTask();
        expectedModel.setTask(taskToDone, completedTask);

        assertCommandSuccess(doneTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        List<Index> validIndexesList = INDEXES_LIST_ONE;
        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(validIndexesList);

        List<Task> tasksToDone = getTasksToDone(validIndexesList);
        String expectedMessage = String.format(DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS, tasksToDone.size());

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        setCompletedTasksInModel(expectedModel, tasksToDone);

        assertCommandSuccess(doneTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexList = new ArrayList<>();
        outOfBoundIndexList.add(outOfBoundIndex);
        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(outOfBoundIndexList);

        String expectedMessage = DoneTaskCommand.MESSAGE_INDEX_OUTOFRANGE + "[" + outOfBoundIndex + "].\n";
        assertCommandFailure(doneTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexesUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexOne = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndexTwo = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        List<Index> outOfBoundIndexList = new ArrayList<>(INDEXES_LIST_TWO);
        outOfBoundIndexList.add(outOfBoundIndexOne);
        outOfBoundIndexList.add(outOfBoundIndexTwo);

        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(outOfBoundIndexList);

        String expectedMessage = DoneTaskCommand.MESSAGE_INDEXES_OUTOFRANGE
                + "[" + outOfBoundIndexOne + ", " + outOfBoundIndexTwo + "].\n";
        assertCommandFailure(doneTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        List<Index> validIndexList = INDEX_LIST_ONE;
        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(validIndexList);

        String expectedMessage = String.format(DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        Task completedTask = new TaskBuilder(taskToDone).buildCompletedTask();
        expectedModel.setTask(taskToDone, completedTask);

        assertCommandSuccess(doneTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of SOChedule's task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSochedule().getTaskList().size());
        List<Index> outOfBoundIndexList = new ArrayList<>();
        outOfBoundIndexList.add(INDEX_SECOND_TASK);

        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(outOfBoundIndexList);

        String expectedMessage = DoneTaskCommand.MESSAGE_INDEX_OUTOFRANGE + "[" + outOfBoundIndex + "].\n";

        assertCommandFailure(doneTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_completedTaskUnfilteredList_throwsCommandException() {
        // set the first task to be completed
        Task taskToDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task completedTask = new TaskBuilder(taskToDone).buildCompletedTask();
        model.setTask(taskToDone, completedTask);

        Index completedTaskIndex = INDEX_FIRST_TASK;
        List<Index> indexList = new ArrayList<>();
        indexList.add(completedTaskIndex);
        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(indexList);

        String expectedMessage = DoneTaskCommand.MESSAGE_TASK_ALREADY_COMPLETE + "[" + completedTaskIndex + "].\n";

        assertCommandFailure(doneTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_completedTasksUnfilteredList_throwsCommandException() {
        // set the first and second task to be completed
        List<Task> tasksToDone = getTasksToDone(INDEXES_LIST_ONE);
        setCompletedTasksInModel(model, tasksToDone);

        List<Index> completedTasksIndexes = INDEXES_LIST_ONE;
        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(completedTasksIndexes);

        String expectedMessage = DoneTaskCommand.MESSAGE_TASKS_ALREADY_COMPLETE
                + completedTasksIndexes.toString() + ".\n";

        assertCommandFailure(doneTaskCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DoneTaskCommand doneFirstTaskCommand = new DoneTaskCommand(INDEX_LIST_ONE);
        DoneTaskCommand doneSecondTaskCommand = new DoneTaskCommand(INDEX_LIST_TWO);

        // same object -> returns true
        assertTrue(doneFirstTaskCommand.equals(doneFirstTaskCommand));

        // same values -> returns true
        DoneTaskCommand doneFirstCommandCopy = new DoneTaskCommand(INDEX_LIST_ONE);
        assertTrue(doneFirstTaskCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(doneFirstTaskCommand.equals(doneSecondTaskCommand));
    }

    private List<Task> getTasksToDone(List<Index> targetIndexes) {
        List<Task> tasksToDone = new ArrayList<>();

        for (Index index: targetIndexes) {
            assert index.getZeroBased() < model.getFilteredTaskList().size();
            Task taskToDone = model.getFilteredTaskList().get(index.getZeroBased());
            tasksToDone.add(taskToDone);
        }
        return tasksToDone;
    }

    /**
     * Sets the given tasks in the given model to be completed tasks.
     *
     * @param model the model where tasks to be completed exist.
     * @param tasksToDone A list of tasks to be completed.
     */
    private void setCompletedTasksInModel(Model model, List<Task> tasksToDone) {
        for (Task task: tasksToDone) {
            Task completedTask = new TaskBuilder(task).buildCompletedTask();
            model.setTask(task, completedTask);
        }
    }
}
