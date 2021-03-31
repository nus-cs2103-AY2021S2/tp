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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class SnoozeCommandTest {

    private Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());

    @Test
    public void execute_allParametersGivenUnfilteredList_success() throws CommandException {
        Task snoozedTask = new TaskBuilder().withTitle("Build a gaming PC")
                .withDescription("buy: coffee, 3080, 40-inch monitor")
                .withDate("15/12/2021").withStatus("not done").withTags("findMoney", "priorities").build();
        SnoozeCommand snoozeCommand = new SnoozeCommand(INDEX_SECOND_TASK, 3);
        String snoozedTaskTitle = snoozedTask.getTitle().fullTitle;

        String expectedMessage = String.format(SnoozeCommand.MESSAGE_SNOOZE_TASK_SUCCESS,
                snoozedTaskTitle, 3);

        Model expectedModel = new ModelManager(getTypicalPlanner(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(1), snoozedTask);

        assertCommandSuccess(snoozeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        SnoozeCommand snoozeCommand = new SnoozeCommand(outOfBoundIndex, 1);

        assertCommandFailure(snoozeCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of planner list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlanner().getTaskList().size());

        SnoozeCommand snoozeCommand = new SnoozeCommand(outOfBoundIndex, 3);

        assertCommandFailure(snoozeCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noDateOnTask_failure() {
        SnoozeCommand snoozeCommand = new SnoozeCommand(INDEX_FIRST_TASK, 3);

        assertCommandFailure(snoozeCommand, model, "The task selected has no date attribute.\n"
                + SnoozeCommand.MESSAGE_USAGE);
    }

    @Test
    public void equals() {
        final SnoozeCommand snoozeCommand = new SnoozeCommand(INDEX_SECOND_TASK, 3);
        // Same object
        assertTrue(snoozeCommand.equals(snoozeCommand));

        SnoozeCommand snoozeCommandWithSameValues = new SnoozeCommand(INDEX_SECOND_TASK, 3);
        // Same values
        assertTrue(snoozeCommand.equals(snoozeCommandWithSameValues));

        assertFalse(snoozeCommand.equals(null));
        assertFalse(snoozeCommand.equals(new DoneCommand(INDEX_SECOND_TASK)));
        assertFalse(snoozeCommand.equals(new SnoozeCommand(INDEX_FIRST_TASK, 3)));
        assertFalse(snoozeCommand.equals(new SnoozeCommand(INDEX_SECOND_TASK, 1)));
    }
}
