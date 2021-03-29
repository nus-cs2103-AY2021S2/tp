package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StatsCommandTest {

    @Test
    public void execute_emptyPlanner_throwsCommandException() {
        Model model = new ModelManager();

        StatsCommand statsCommand = new StatsCommand();

        assertCommandFailure(statsCommand, model, StatsCommand.MESSAGE_NO_TASKS);
    }

    @Test
    public void execute_nonEmptyPlanner_success() {
        Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());

        String expectedMessage = String.format(StatsCommand.MESSAGE_SUCCESS, 7, 71.43, 0);

        ModelManager expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());

        StatsCommand statsCommand = new StatsCommand();

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel);
    }
}
