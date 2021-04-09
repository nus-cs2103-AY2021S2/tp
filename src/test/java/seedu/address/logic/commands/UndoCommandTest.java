package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_UNDO_COMMAND;
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

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskTracker(), new UserPrefs());


    @Test
    public void execute_validUndoDeleteCommand_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        model.commitTaskTracker(model.getTaskTracker());

        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TASK);

        String expectedDeleteMessage = String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedDeleteModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedDeleteModel.commitTaskTracker(expectedDeleteModel.getTaskTracker());
        expectedDeleteModel.deleteTask(taskToDelete);
        expectedDeleteModel.commitTaskTracker(expectedDeleteModel.getTaskTracker());

        assertCommandSuccess(deleteCommand, model, expectedDeleteMessage, expectedDeleteModel);
        model.commitTaskTracker(model.getTaskTracker());


        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validUndoClearCommand_success() {
        model.commitTaskTracker(model.getTaskTracker());

        Model expectedClearModel = new ModelManager(getTypicalTaskTracker(), new UserPrefs());
        expectedClearModel.setTaskTracker(new TaskTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedClearModel);
        model.commitTaskTracker(model.getTaskTracker());

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

    }

    @Test
    public void execute_invalidCurrentStatePointer_throwsCommandException() {
        assertCommandFailure(new UndoCommand(), model,
            String.format(MESSAGE_INVALID_UNDO_COMMAND, UndoCommand.MESSAGE_USAGE));
    }

}
