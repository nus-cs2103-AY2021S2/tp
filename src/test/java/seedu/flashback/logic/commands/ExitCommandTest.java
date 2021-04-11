package seedu.flashback.logic.commands;

import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashback.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
