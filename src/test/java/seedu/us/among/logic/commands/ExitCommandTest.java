package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandResult.exitCommandResult;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = exitCommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
