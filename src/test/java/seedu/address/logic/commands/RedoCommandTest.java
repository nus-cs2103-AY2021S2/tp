package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REDO_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskTracker;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Task;

public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());

    @Test
    public void execute_validRedoDeleteCommand_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        model.commitTaskTracker(model.getTaskTracker());

        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TASK);

        String expectedDeleteMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.commitTaskTracker(expectedModel.getTaskTracker());
        expectedModel.deleteTask(taskToDelete);
        expectedModel.commitTaskTracker(expectedModel.getTaskTracker());

        assertCommandSuccess(deleteCommand, model, expectedDeleteMessage, expectedModel);
        model.commitTaskTracker(model.getTaskTracker());

        ModelManager expectedUndoModel = new ModelManager(expectedModel.undoTaskTracker(), new UserPrefs());
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedUndoModel);

        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validRedoClearCommand_success() {
        model.commitTaskTracker(model.getTaskTracker());

        Model expectedModel = new ModelManager(getTypicalTaskTracker(), new UserPrefs());
        expectedModel.commitTaskTracker(expectedModel.getTaskTracker());
        expectedModel.setTaskTracker(new TaskTracker());
        expectedModel.commitTaskTracker(expectedModel.getTaskTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
        model.commitTaskTracker(model.getTaskTracker());

        ModelManager expectedUndoModel = new ModelManager(expectedModel.undoTaskTracker(), new UserPrefs());

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedUndoModel);

        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_invalidCurrentStatePointer_throwsCommandException() {
        assertCommandFailure(new RedoCommand(), model,
            String.format(MESSAGE_INVALID_REDO_COMMAND, RedoCommand.MESSAGE_USAGE));
    }
}
