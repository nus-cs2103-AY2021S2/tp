package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ScheduleCommand.SHOWING_SCHEDULE_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ScheduleCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_schedule_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SCHEDULE_MESSAGE, true, false, false, false);
        assertCommandSuccess(new ScheduleCommand(), model, expectedCommandResult, expectedModel);
    }
}
