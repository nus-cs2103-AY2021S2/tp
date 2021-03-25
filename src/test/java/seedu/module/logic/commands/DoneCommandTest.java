package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_FOURTH_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Task;

public class DoneCommandTest {
    private Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListWithStartTime_success() {
        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        Task referenceTask = new Task(taskToMarkDone.getName(), taskToMarkDone.getStartTime(),
                taskToMarkDone.getDeadline(), taskToMarkDone.getModule(), taskToMarkDone.getDescription(),
                taskToMarkDone.getWorkload(), new DoneStatus(true), taskToMarkDone.getTags());
        expectedModel.setTask(taskToMarkDone, referenceTask);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, referenceTask);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListWithoutStartTime_success() {
        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FOURTH_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FOURTH_TASK);

        ModelManager expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        Task referenceTask = new Task(taskToMarkDone.getName(), taskToMarkDone.getDeadline(),
                taskToMarkDone.getModule(), taskToMarkDone.getDescription(), taskToMarkDone.getWorkload(),
                new DoneStatus(true), taskToMarkDone.getTags());
        expectedModel.setTask(taskToMarkDone, referenceTask);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, referenceTask);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_doneAlreadyUnfilteredList_throwsCommandException() {
        Task originalTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskDone = new Task(originalTask.getName(), originalTask.getStartTime(), originalTask.getDeadline(),
                originalTask.getModule(), originalTask.getDescription(), originalTask.getWorkload(),
                new DoneStatus(true), originalTask.getTags());
        model.setTask(originalTask, taskDone);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        assertCommandFailure(doneCommand, model, DoneCommand.MESSAGE_TASK_ALREADY_DONE);

        //Reset to original
        model.setTask(taskDone, originalTask);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        Model expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        Task referenceTask = new Task(taskToMarkDone.getName(), taskToMarkDone.getStartTime(),
                taskToMarkDone.getDeadline(), taskToMarkDone.getModule(), taskToMarkDone.getDescription(),
                taskToMarkDone.getWorkload(), new DoneStatus(true), taskToMarkDone.getTags());
        expectedModel.setTask(taskToMarkDone, referenceTask);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, referenceTask);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of module book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleBook().getTaskList().size());

        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_doneAlreadyFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task originalTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskDone = new Task(originalTask.getName(), originalTask.getStartTime(), originalTask.getDeadline(),
                originalTask.getModule(), originalTask.getDescription(), originalTask.getWorkload(),
                new DoneStatus(true), originalTask.getTags());
        model.setTask(originalTask, taskDone);
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        assertCommandFailure(doneCommand, model, DoneCommand.MESSAGE_TASK_ALREADY_DONE);

        //Reset to original
        model.setTask(taskDone, originalTask);
    }

    @Test
    public void equals() {
        DoneCommand doneFirstCommand = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand doneSecondCommand = new DoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(INDEX_FIRST_TASK);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

}
