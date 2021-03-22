package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CountdownCommand.
 */
public class CountdownCommandTest {
    private Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToCountdown = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        long daysLeft = LocalDate.now().until(taskToCountdown.getDeadline().getDate(),
                ChronoUnit.DAYS);

        CountdownCommand countdownCommand = new CountdownCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_COUNTDOWN_TASK_SUCCESS,
                daysLeft, taskToCountdown);

        ModelManager expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);

        CountdownCommand countdownCommand = new CountdownCommand(outOfBoundIndex);

        assertCommandFailure(countdownCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToCountdown = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        long daysLeft = LocalDate.now().until(taskToCountdown.getDeadline().getDate(),
                ChronoUnit.DAYS);
        CountdownCommand countdownCommand = new CountdownCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_COUNTDOWN_TASK_SUCCESS,
                daysLeft, taskToCountdown);

        Model expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());

        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);
        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of planner list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlanner().getTaskList().size());

        CountdownCommand countdownCommand = new CountdownCommand(outOfBoundIndex);

        assertCommandFailure(countdownCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CountdownCommand countdownFirstCommand = new CountdownCommand(INDEX_FIRST_TASK);
        CountdownCommand countdownSecondCommand = new CountdownCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(countdownFirstCommand.equals(countdownFirstCommand));

        // same values -> returns true
        CountdownCommand countdownFirstCommandCopy = new CountdownCommand(INDEX_FIRST_TASK);
        assertTrue(countdownFirstCommand.equals(countdownFirstCommandCopy));

        // different types -> returns false
        assertFalse(countdownFirstCommand.equals(1));

        // null -> returns false
        assertFalse(countdownFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(countdownFirstCommand.equals(countdownSecondCommand));
    }
}
