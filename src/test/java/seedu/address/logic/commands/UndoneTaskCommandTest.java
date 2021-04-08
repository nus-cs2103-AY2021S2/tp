package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalSocheduleWithCompletedTask;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UndoneTaskCommand}.
 */
public class UndoneTaskCommandTest {
    private Model model = new ModelManager(getTypicalSocheduleWithCompletedTask(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToUndone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        UndoneTaskCommand undoneTaskCommand = new UndoneTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(UndoneTaskCommand.MESSAGE_UNDONE_TASK_SUCCESS, taskToUndone);

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        // copy the taskToUdone so that 'undone' it in expectedModel does not affect the task in actual model.
        Task taskToUndoneCopy = taskToUndone.getCopy();
        expectedModel.setTask(taskToUndone, taskToUndoneCopy);
        expectedModel.undoneTask(taskToUndoneCopy);

        assertCommandSuccess(undoneTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UndoneTaskCommand undoneTaskCommand = new UndoneTaskCommand(outOfBoundIndex);

        assertCommandFailure(undoneTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToUndone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task uncompletedTask = new TaskBuilder(taskToUndone).build();
        UndoneTaskCommand undoneTaskCommand = new UndoneTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(UndoneTaskCommand.MESSAGE_UNDONE_TASK_SUCCESS, taskToUndone);

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), uncompletedTask);

        assertCommandSuccess(undoneTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of SOChedule's task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSochedule().getTaskList().size());

        UndoneTaskCommand undoneTaskCommand = new UndoneTaskCommand(outOfBoundIndex);

        assertCommandFailure(undoneTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_uncompletedTask_throwsCommandException() {
        // Only the first task in our task list is marked as completed, the rest are all uncompleted.
        Index uncompletedTaskIndex = Index.fromOneBased(2);
        UndoneTaskCommand undoneTaskCommand = new UndoneTaskCommand(uncompletedTaskIndex);

        assertCommandFailure(undoneTaskCommand, model, UndoneTaskCommand.MESSAGE_TASK_ALREADY_UNCOMPLETED);
    }

    @Test
    public void equals() {
        UndoneTaskCommand undoneFirstTaskCommand = new UndoneTaskCommand(INDEX_FIRST_TASK);
        UndoneTaskCommand undoneSecondTaskCommand = new UndoneTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(undoneFirstTaskCommand.equals(undoneFirstTaskCommand));

        // same values -> returns true
        UndoneTaskCommand undoneFirstCommandCopy = new UndoneTaskCommand(INDEX_FIRST_TASK);
        assertTrue(undoneFirstTaskCommand.equals(undoneFirstCommandCopy));

        // different types -> returns false
        assertFalse(undoneFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(undoneFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(undoneFirstTaskCommand.equals(undoneSecondTaskCommand));
    }

}
