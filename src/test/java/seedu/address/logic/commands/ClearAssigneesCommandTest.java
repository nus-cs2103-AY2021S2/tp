package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
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
 * {@code ClearAssigneesCommand}.
 */
public class ClearAssigneesCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToClear = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        Task clearedTask = new TaskBuilder()
                .withTitle(taskToClear.getTitle().taskTitle)
                .withDescription(taskToClear.getDescription().desc)
                .withDeadline(taskToClear.getDeadline().unformattedDate)
                .withTaskStatus(taskToClear.getTaskStatus().status)
                .withPriority(taskToClear.getPriority().priority)
                .build();

        ClearAssigneesCommand clearAssigneesCommand = new ClearAssigneesCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(clearAssigneesCommand.MESSAGE_CLEARED_ASSIGNEES_SUCCESS,
                clearedTask);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTask(taskToClear, clearedTask);

        assertCommandSuccess(clearAssigneesCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        ClearAssigneesCommand clearAssigneesCommand = new ClearAssigneesCommand(outOfBoundIndex);

        assertCommandFailure(clearAssigneesCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToClear = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        Task clearedTask = new TaskBuilder()
                .withTitle(taskToClear.getTitle().taskTitle)
                .withDescription(taskToClear.getDescription().desc)
                .withDeadline(taskToClear.getDeadline().unformattedDate)
                .withTaskStatus(taskToClear.getTaskStatus().status)
                .withPriority(taskToClear.getPriority().priority)
                .build();

        ClearAssigneesCommand clearAssigneesCommand = new ClearAssigneesCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(ClearAssigneesCommand.MESSAGE_CLEARED_ASSIGNEES_SUCCESS,
                clearedTask);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.setTask(taskToClear, clearedTask);
        showNoTask(expectedModel);

        assertCommandSuccess(clearAssigneesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        ClearAssigneesCommand clearAssigneesCommand = new ClearAssigneesCommand(outOfBoundIndex);

        assertCommandFailure(clearAssigneesCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }

    @Test
    public void equals() {
        ClearAssigneesCommand firstClearCommand = new ClearAssigneesCommand(INDEX_FIRST_TASK);
        ClearAssigneesCommand secondClearCommand = new ClearAssigneesCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(firstClearCommand.equals(firstClearCommand));

        // same values -> returns true
        ClearAssigneesCommand firstClearCommandCopy = new ClearAssigneesCommand(INDEX_FIRST_TASK);
        assertTrue(firstClearCommand.equals(firstClearCommandCopy));

        // different types -> returns false
        assertFalse(firstClearCommand.equals(1));

        // null -> returns false
        assertFalse(firstClearCommand.equals(null));

        // different person -> returns false
        assertFalse(firstClearCommand.equals(secondClearCommand));
    }
}
