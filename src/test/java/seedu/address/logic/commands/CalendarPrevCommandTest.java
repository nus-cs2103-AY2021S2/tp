package seedu.address.logic.commands;

import static seedu.address.logic.commands.CalendarPrevCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.CalendarDirection;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class CalendarPrevCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_calendarPrev_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, true, CalendarDirection.PREV, false);
        assertCommandSuccess(new CalendarPrevCommand(), model, expectedCommandResult, expectedModel);
    }
}
