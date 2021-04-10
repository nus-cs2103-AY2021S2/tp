package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskify.logic.commands.CommandTestUtil.showTasksAtIndexes;
import static seedu.taskify.logic.commands.util.DeleteUtil.MESSAGE_NO_TASKS_OF_GIVEN_STATUS;
import static seedu.taskify.testutil.TypicalIndexes.INDEXES_FIRST_TO_THIRD_TASK;
import static seedu.taskify.testutil.TypicalTasks.TASK_3;
import static seedu.taskify.testutil.TypicalTasks.TASK_5;
import static seedu.taskify.testutil.TypicalTasks.getTypicalTaskify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.UserPrefs;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteByStatusCommand}.
 */
public class DeleteByStatusCommandTest {

    private static final String MESSAGE_DELETE_COMPLETED_TASKS_SUCCESS =
            "Deleted Tasks: \n" + TASK_3.toString() + "\n\n" + TASK_5.toString() + "\n\n";

    private Model model = new ModelManager(getTypicalTaskify(), new UserPrefs());

    private void deleteTasksFromModel(Model model, List<Task> tasksToDelete) {
        for (Task toDelete : tasksToDelete) {
            model.deleteTask(toDelete);
        }
    }

    @Test
    public void execute_deleteByStatus_success() {
        List<Task> tasksToDelete = new ArrayList<>();
        tasksToDelete.add(TASK_3);
        tasksToDelete.add(TASK_5);

        DeleteByStatusCommand deleteMulCommand = new DeleteByStatusCommand(new Status(StatusType.COMPLETED));
        String expectedMessage = MESSAGE_DELETE_COMPLETED_TASKS_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getTaskify(), new UserPrefs());
        deleteTasksFromModel(expectedModel, tasksToDelete);

        assertCommandSuccess(deleteMulCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteByStatusButNoSuchTasks_throwsCommandException() {
        showTasksAtIndexes(model, INDEXES_FIRST_TO_THIRD_TASK); // none of these tasks are expired
        DeleteByStatusCommand deleteMulCommand = new DeleteByStatusCommand(new Status(StatusType.EXPIRED));

        assertCommandFailure(deleteMulCommand, model, MESSAGE_NO_TASKS_OF_GIVEN_STATUS);
    }

    @Test
    public void equals() {

        DeleteByStatusCommand firstCommand = new DeleteByStatusCommand(new Status(StatusType.COMPLETED));
        DeleteByStatusCommand secondCommand = new DeleteByStatusCommand(new Status(StatusType.EXPIRED));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeleteByStatusCommand secondCommandCopy = new DeleteByStatusCommand(new Status(StatusType.EXPIRED));
        assertTrue(secondCommand.equals(secondCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different task -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

}
