package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
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

public class NotDoneCommandTest {
    private Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task originalTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskToMarkUndone = new Task(originalTask.getName(), originalTask.getStartTime(),
                originalTask.getDeadline(), originalTask.getModule(), originalTask.getDescription(),
                originalTask.getWorkload(), new DoneStatus(true), originalTask.getTags());
        model.setTask(originalTask, taskToMarkUndone);

        NotDoneCommand notDoneCommand = new NotDoneCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        expectedModel.setTask(expectedModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), originalTask);

        String expectedMessage = String.format(NotDoneCommand.MESSAGE_NOT_DONE_TASK_SUCCESS, originalTask);

        assertCommandSuccess(notDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        NotDoneCommand notDoneCommand = new NotDoneCommand(outOfBoundIndex);

        assertCommandFailure(notDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_doneAlreadyUnfilteredList_throwsCommandException() {
        NotDoneCommand notDoneCommand = new NotDoneCommand(INDEX_FIRST_TASK);

        assertCommandFailure(notDoneCommand, model, NotDoneCommand.MESSAGE_TASK_ALREADY_NOT_DONE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task originalTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskToMarkUndone = new Task(originalTask.getName(), originalTask.getStartTime(),
                originalTask.getDeadline(), originalTask.getModule(), originalTask.getDescription(),
                originalTask.getWorkload(), new DoneStatus(true), originalTask.getTags());
        model.setTask(originalTask, taskToMarkUndone);

        NotDoneCommand notDoneCommand = new NotDoneCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
        expectedModel.setTask(expectedModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), originalTask);

        String expectedMessage = String.format(NotDoneCommand.MESSAGE_NOT_DONE_TASK_SUCCESS, originalTask);

        assertCommandSuccess(notDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of module book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleBook().getTaskList().size());

        NotDoneCommand notDoneCommand = new NotDoneCommand(outOfBoundIndex);

        assertCommandFailure(notDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_doneAlreadyFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        NotDoneCommand notDoneCommand = new NotDoneCommand(INDEX_FIRST_TASK);

        assertCommandFailure(notDoneCommand, model, NotDoneCommand.MESSAGE_TASK_ALREADY_NOT_DONE);
    }

    @Test
    public void equals() {
        NotDoneCommand notDoneFirstCommand = new NotDoneCommand(INDEX_FIRST_TASK);
        NotDoneCommand notDoneSecondCommand = new NotDoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(notDoneFirstCommand.equals(notDoneFirstCommand));

        // same values -> returns true
        NotDoneCommand notDoneFirstCommandCopy = new NotDoneCommand(INDEX_FIRST_TASK);
        assertTrue(notDoneFirstCommand.equals(notDoneFirstCommandCopy));

        // different types -> returns false
        assertFalse(notDoneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(notDoneFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(notDoneFirstCommand.equals(notDoneSecondCommand));
    }
}
