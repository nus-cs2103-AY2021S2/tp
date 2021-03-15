package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 *
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());

    @Test
    public void execute_updateNotDoneStatus_success() {
        Task taskToBeDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);
        Task taskSetToDone = DoneCommand.setTaskStatusAsDone(taskToBeDone);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskSetToDone);
        ModelManager expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());
        expectedModel.setTask(taskToBeDone, taskSetToDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_statusIsDone_success() {
        Task taskToBeDone = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_SECOND_TASK);
        Task taskSetToDone = DoneCommand.setTaskStatusAsDone(taskToBeDone);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskSetToDone);
        ModelManager expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());
        expectedModel.setTask(taskToBeDone, taskSetToDone);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final DoneCommand standardCommand = new DoneCommand(INDEX_FIRST_TASK);

        assertTrue(standardCommand.equals(standardCommand));

        DoneCommand doneCommandWithIdenticalIndex = new DoneCommand(INDEX_FIRST_TASK);
        assertTrue(standardCommand.equals(doneCommandWithIdenticalIndex));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new DeleteCommand(INDEX_FIRST_TASK)));

        assertFalse(standardCommand.equals(new DoneCommand(INDEX_SECOND_TASK)));
    }

}
