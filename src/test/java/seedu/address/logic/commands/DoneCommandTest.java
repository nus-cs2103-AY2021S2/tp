package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToFinish = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToFinish);

        ModelManager expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.finishTask(taskToFinish);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        //showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToFinish = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToFinish);

        Model expectedModel = new ModelManager(model.getTaskTracker(), new UserPrefs());
        expectedModel.finishTask(taskToFinish);
        //showNoTask(expectedModel);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskTracker().getTaskList().size());

        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneCommand finishFirstCommand = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand finishSecondCommand = new DoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(finishFirstCommand.equals(finishFirstCommand));

        // same values -> returns true
        DoneCommand deleteFirstCommandCopy = new DoneCommand(INDEX_FIRST_TASK);
        assertTrue(finishFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(finishFirstCommand.equals(1));

        // null -> returns false
        assertFalse(finishFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(finishFirstCommand.equals(finishSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
