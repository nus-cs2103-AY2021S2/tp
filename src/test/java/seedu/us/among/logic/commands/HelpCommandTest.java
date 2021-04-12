package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandResult.helpCommandResult;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;

public class HelpCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = helpCommandResult(HelpCommand.SHOWING_HELP_MESSAGE);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
