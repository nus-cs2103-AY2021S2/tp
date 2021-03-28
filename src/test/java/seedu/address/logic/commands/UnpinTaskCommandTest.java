package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalSochedule;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnpinTaskCommand}.
 */
public class UnpinTaskCommandTest {
    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_validIndexList_success() {
        Task taskToUnpin = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskToUnpinCopy = copyTask(taskToUnpin); //make a copy to preserve integrity
        model.setTask(taskToUnpin, taskToUnpinCopy);
        taskToUnpinCopy.pin(); //have to initialize to pinned
        UnpinTaskCommand unpinTaskCommand = new UnpinTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = UnpinTaskCommand.MESSAGE_UNPIN_TASK_SUCCESS;
        try {
            CommandResult result = unpinTaskCommand.execute(model);
            assertEquals(result, new CommandResult(expectedMessage));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        Task copiedTask = copyTask(taskToUnpin);
        expectedModel.setTask(taskToUnpin, copiedTask);
        copiedTask.pin();
        expectedModel.unpinTask(copiedTask);

        assertEquals(model.getFilteredTaskList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_invalidIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnpinTaskCommand unpinTaskCommand = new UnpinTaskCommand(outOfBoundIndex);

        assertCommandFailure(unpinTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnpinTaskCommand unpinFirstTaskCommand = new UnpinTaskCommand(INDEX_FIRST_TASK);
        UnpinTaskCommand unpinSecondTaskCommand = new UnpinTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unpinFirstTaskCommand.equals(unpinFirstTaskCommand));

        // same values -> returns true
        UnpinTaskCommand unpinFirstTaskCommandCopy = new UnpinTaskCommand(INDEX_FIRST_TASK);
        assertTrue(unpinFirstTaskCommand.equals(unpinFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(unpinFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(unpinFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(unpinFirstTaskCommand.equals(unpinSecondTaskCommand));

    }

    /**
     * Copies the task given and returns a new task with the same details as the given task.
     *
     * @param taskToCopy task to be copied, here is the task to pin.
     * @return a copied task.
     */
    private static Task copyTask(Task taskToCopy) {
        Name taskName = taskToCopy.getName();
        Date deadline = taskToCopy.getDeadline();
        Priority priority = taskToCopy.getPriority();
        Set<Category> categories = taskToCopy.getCategories();
        Set<Tag> tags = taskToCopy.getTags();

        Task copiedTask = new Task(taskName, deadline, priority, categories, tags);
        if (taskToCopy.isComplete()) {
            copiedTask.markTaskAsDone();
        }
        if (taskToCopy.isPinned()) {
            copiedTask.pin();
        }
        return copiedTask;
    }
}
