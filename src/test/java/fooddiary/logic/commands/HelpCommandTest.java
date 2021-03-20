package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import fooddiary.model.Model;
import fooddiary.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(null, SHOWING_HELP_MESSAGE, true, false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
