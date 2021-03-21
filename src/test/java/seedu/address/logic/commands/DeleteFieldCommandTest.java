//@@author mesyeux
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Planner;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFieldCommand}.
 */
public class DeleteFieldCommandTest {
    private Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());

    @Test
    public void execute_validIndexValidFieldUnfilteredList_success() {
        Task taskToDeleteFieldFrom = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskWithFieldDeleted = new TaskBuilder().withTitle("Assignment 79")
                .withDescription("Build the next Google").withRecurringSchedule("[10/03/2021][Mon][biweekly]")
                .withDeadline("13/05/1998").withStartTime("1230").withStatus("not done").build();
        String fieldToDelete = "t/";

        DeleteFieldCommand deleteFieldCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, fieldToDelete);

        String expectedMessage = String.format(DeleteFieldCommand.MESSAGE_DELETE_FIELD_SUCCESS, taskWithFieldDeleted);

        ModelManager expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());
        expectedModel.setTask(taskToDeleteFieldFrom, taskWithFieldDeleted);

        assertCommandSuccess(deleteFieldCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(outOfBoundIndex, "t/");

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexTitleFieldUnfilteredList_throwsCommandException() {
        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, "n/");

        assertCommandFailure(deleteCommand, model, "Cannot delete title field.");
    }

    @Test
    public void execute_validIndexInvalidFieldUnfilteredList_throwsCommandException() {
        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, "pp/");

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_validIndexValidFieldFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskWithFieldDeleted = new TaskBuilder(taskInFilteredList).withDescription("").build();
        String fieldToDelete = "d/";

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, fieldToDelete);

        String expectedMessage = String.format(DeleteFieldCommand.MESSAGE_DELETE_FIELD_SUCCESS, taskWithFieldDeleted);

        Model expectedModel = new ModelManager(new Planner(model.getPlanner()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), taskWithFieldDeleted);

        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of planner list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlanner().getTaskList().size());

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(outOfBoundIndex, "d/");

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexTitleFieldFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, "n/");

        assertCommandFailure(deleteCommand, model, "Cannot delete title field.");
    }

    @Test
    public void execute_validIndexInvalidFieldFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, "pp/");

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void equals() {
        DeleteFieldCommand deleteFirstCommand = new DeleteFieldCommand(INDEX_FIRST_TASK, "d/");
        DeleteFieldCommand deleteSecondCommand = new DeleteFieldCommand(INDEX_SECOND_TASK, "d/");

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFieldCommand deleteFirstCommandCopy = new DeleteFieldCommand(INDEX_FIRST_TASK, "d/");
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
