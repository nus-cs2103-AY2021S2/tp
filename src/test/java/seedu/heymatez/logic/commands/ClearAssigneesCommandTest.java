package seedu.heymatez.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.logic.commands.ClearAssigneesCommand.MESSAGE_LIST_IS_EMPTY;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.heymatez.testutil.TypicalTasks.getTypicalHeyMatez;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ClearAssigneesCommand}.
 */
public class ClearAssigneesCommandTest {
    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

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

        String expectedMessage = String.format(clearAssigneesCommand.MESSAGE_ASSIGNEES_SET_IS_EMPTY,
                clearedTask);

        ModelManager expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
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
    public void execute_invalidIntegerUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(Integer.MAX_VALUE + 1);
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

        String expectedMessage = String.format(ClearAssigneesCommand.MESSAGE_ASSIGNEES_SET_IS_EMPTY,
                clearedTask);

        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());

        expectedModel.setTask(taskToClear, clearedTask);
        showNoTask(expectedModel);

        assertCommandSuccess(clearAssigneesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of HeyMatez task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHeyMatez().getTaskList().size());

        ClearAssigneesCommand clearAssigneesCommand = new ClearAssigneesCommand(outOfBoundIndex);

        assertCommandFailure(clearAssigneesCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showNoTask(model);

        Index givenIndex = INDEX_SECOND_TASK;

        ClearAssigneesCommand clearAssigneesCommand = new ClearAssigneesCommand(givenIndex);

        assertCommandFailure(clearAssigneesCommand, model, MESSAGE_LIST_IS_EMPTY);
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
