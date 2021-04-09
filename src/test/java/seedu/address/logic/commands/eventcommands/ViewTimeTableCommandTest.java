package seedu.address.logic.commands.eventcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.eventcommands.ViewTimeTableCommand.SHOWING_OPEN_MESSAGE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewTimeTableCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_OPEN_MESSAGE, false, false, true);
        assertCommandSuccess(new ViewTimeTableCommand(LocalDate.now()), model, expectedCommandResult, expectedModel);
    }
}
