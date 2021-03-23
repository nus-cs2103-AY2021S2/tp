package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

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
 * {@code DoneTaskCommand}.
 */
public class UndoTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMarkUncompleted = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());

        Task undoTask = new TaskBuilder(taskToMarkUncompleted).withTaskStatus(TaskBuilder.DEFAULT_STATUS).build();

        UndoTaskCommand undoTaskCommand = new UndoTaskCommand(INDEX_THIRD_TASK);

        String expectedMessage = UndoTaskCommand.MESSAGE_UNDO_TASK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(taskToMarkUncompleted, undoTask);

        assertCommandSuccess(undoTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UndoTaskCommand undoTaskCommand = new UndoTaskCommand(outOfBoundIndex);

        assertCommandFailure(undoTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_THIRD_TASK);

        Task taskToMarkUncompleted = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task doneTask = new TaskBuilder(taskToMarkUncompleted).withTaskStatus(TaskBuilder.COMPLETED_STATUS).build();

        UndoTaskCommand undoCommand = new UndoTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = UndoTaskCommand.MESSAGE_UNDO_TASK_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.setTask(taskToMarkUncompleted, doneTask);
        showNoTask(expectedModel);

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void task_already_markedUncompleted() {
        UndoTaskCommand undoTaskCommand = new UndoTaskCommand(INDEX_FIRST_TASK);

        assertCommandFailure(undoTaskCommand, model, UndoTaskCommand.MESSAGE_TASK_ALREADY_MARKED_UNCOMPLETED);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        UndoTaskCommand undoTaskCommand = new UndoTaskCommand(outOfBoundIndex);

        assertCommandFailure(undoTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UndoTaskCommand undoFirstCommand = new UndoTaskCommand(INDEX_FIRST_TASK);
        UndoTaskCommand undoSecondCommand = new UndoTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(undoFirstCommand.equals(undoFirstCommand));

        // same values -> returns true
        UndoTaskCommand undoFirstCommandCopy = new UndoTaskCommand(INDEX_FIRST_TASK);
        assertTrue(undoFirstCommand.equals(undoFirstCommandCopy));

        // different types -> returns false
        assertFalse(undoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(undoFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(undoFirstCommand.equals(undoSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
