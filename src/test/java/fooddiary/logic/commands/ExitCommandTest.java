package fooddiary.logic.commands;

import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import org.junit.jupiter.api.Test;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(null, MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
