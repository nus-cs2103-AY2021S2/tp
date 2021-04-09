package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToFinish = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToFinish);

        ModelManager expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.finishTask(taskToFinish);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);

        // Top boundary
        Index inBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size());
        taskToFinish = model.getFilteredTaskList().get(inBoundIndex.getZeroBased());
        doneCommand = new DoneCommand(inBoundIndex);

        expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToFinish);

        expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.finishTask(taskToFinish);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Task taskToFinish = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()); // Bottom boundary
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToFinish);

        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.finishTask(taskToFinish);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);

        // Top boundary
        Index inBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size());
        taskToFinish = model.getFilteredTaskList().get(inBoundIndex.getZeroBased());
        doneCommand = new DoneCommand(inBoundIndex);

        expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToFinish);

        expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.finishTask(taskToFinish);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskTracker().getTaskList().size());

        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        DoneCommand finishFirstCommand = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand finishSecondCommand = new DoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(finishFirstCommand.equals(finishFirstCommand));
        assertTrue(finishSecondCommand.equals(finishSecondCommand));

        // same values -> returns true
        DoneCommand finishFirstCommandCopy = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand finishSecondCommandCopy = new DoneCommand(INDEX_SECOND_TASK);
        assertTrue(finishFirstCommand.equals(finishFirstCommandCopy));
        assertTrue(finishSecondCommand.equals(finishSecondCommandCopy));

        // different types -> returns false
        assertFalse(finishFirstCommand.equals(1)); // integer
        assertFalse(finishSecondCommand.equals(1));
        assertFalse(finishFirstCommand.equals("1")); // string
        assertFalse(finishSecondCommand.equals("1"));
        assertFalse(finishFirstCommand.equals(new Object())); // object
        assertFalse(finishSecondCommand.equals(new Object()));
        assertFalse(finishFirstCommand.equals(new DeleteCommand(INDEX_FIRST_TASK))); // other command
        assertFalse(finishSecondCommand.equals(new DeleteCommand(INDEX_FIRST_TASK)));

        // null -> returns false
        assertFalse(finishFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(finishFirstCommand.equals(finishSecondCommand));
    }
}
