package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
