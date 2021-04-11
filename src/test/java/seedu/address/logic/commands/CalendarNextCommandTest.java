package seedu.address.logic.commands;

import static seedu.address.logic.commands.CalendarNextCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.CalendarDirection;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class CalendarNextCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_calendarNext_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, true, CalendarDirection.NEXT, false);
        assertCommandSuccess(new CalendarNextCommand(), model, expectedCommandResult, expectedModel);
    }
}
