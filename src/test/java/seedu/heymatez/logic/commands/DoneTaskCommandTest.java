package seedu.heymatez.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.heymatez.testutil.TypicalTasks.getTypicalHeyMatez;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneTaskCommand}.
 */
public class DoneTaskCommandTest {
    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        Task doneTask = new TaskBuilder(taskToMarkDone).withTaskStatus(TaskBuilder.COMPLETED_STATUS).build();

        DoneTaskCommand doneCommand = new DoneTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        expectedModel.setTask(taskToMarkDone, doneTask);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneTaskCommand doneCommand = new DoneTaskCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToMarkDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task doneTask = new TaskBuilder(taskToMarkDone).withTaskStatus(TaskBuilder.COMPLETED_STATUS).build();

        DoneTaskCommand doneCommand = new DoneTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS;

        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());

        expectedModel.setTask(taskToMarkDone, doneTask);
        showNoTask(expectedModel);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyFilteredList_success() {
        HeyMatez heyMatez = new HeyMatez();

        model = new ModelManager(heyMatez, new UserPrefs());

        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());

        Index givenIndex = INDEX_SECOND_TASK;

        DoneTaskCommand doneTaskCommand = new DoneTaskCommand(givenIndex);

        assertCommandSuccess(doneTaskCommand, model, MESSAGE_EMPTY_TASK_LIST, expectedModel);
    }

    @Test
    public void task_already_markedCompleted() {
        DoneTaskCommand doneCommand = new DoneTaskCommand(INDEX_THIRD_TASK);

        assertCommandFailure(doneCommand, model, DoneTaskCommand.MESSAGE_TASK_ALREADY_MARKED_DONE);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHeyMatez().getTaskList().size());

        DoneTaskCommand doneCommand = new DoneTaskCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneTaskCommand doneFirstCommand = new DoneTaskCommand(INDEX_FIRST_TASK);
        DoneTaskCommand doneSecondCommand = new DoneTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneTaskCommand doneFirstCommandCopy = new DoneTaskCommand(INDEX_FIRST_TASK);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
