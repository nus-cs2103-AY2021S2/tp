package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
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
 * {@code PinTaskCommand}.
 */
public class PinTaskCommandTest {
    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToPin = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        PinTaskCommand pinTaskCommand = new PinTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = PinTaskCommand.MESSAGE_PIN_TASK_SUCCESS;
        try {
            CommandResult result = pinTaskCommand.execute(model);
            assertEquals(result, new CommandResult(expectedMessage));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        Task copiedTask = copyTask(taskToPin);
        expectedModel.setTask(taskToPin, copiedTask);
        expectedModel.pinTask(copiedTask);

        assertEquals(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), copiedTask);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        PinTaskCommand pinTaskCommand = new PinTaskCommand(outOfBoundIndex);

        assertCommandFailure(pinTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Task taskToPin = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        PinTaskCommand pinTaskCommand = new PinTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = PinTaskCommand.MESSAGE_PIN_TASK_SUCCESS;
        try {
            CommandResult result = pinTaskCommand.execute(model);
            assertEquals(result, new CommandResult(expectedMessage));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
        Task copiedTask = copyTask(taskToPin);
        expectedModel.setTask(taskToPin, copiedTask);
        expectedModel.pinTask(copiedTask);

        assertEquals(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), copiedTask);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of sochedule's task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSochedule().getTaskList().size());

        PinTaskCommand pinTaskCommand = new PinTaskCommand(outOfBoundIndex);

        assertCommandFailure(pinTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PinTaskCommand pinFirstTaskCommand = new PinTaskCommand(INDEX_FIRST_TASK);
        PinTaskCommand pinSecondTaskCommand = new PinTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(pinFirstTaskCommand.equals(pinFirstTaskCommand));

        // same values -> returns true
        PinTaskCommand deleteFirstCommandCopy = new PinTaskCommand(INDEX_FIRST_TASK);
        assertTrue(pinFirstTaskCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(pinFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(pinFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(pinFirstTaskCommand.equals(pinSecondTaskCommand));
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
        return copiedTask;
    }

    /**
     * Updates {@code model}'s filtered list to show no task.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
