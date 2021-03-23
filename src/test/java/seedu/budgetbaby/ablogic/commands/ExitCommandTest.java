package seedu.budgetbaby.ablogic.commands;

import static seedu.budgetbaby.ablogic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.budgetbaby.ablogic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.abmodel.Model;
import seedu.budgetbaby.abmodel.ModelManager;
import seedu.budgetbaby.logic.commands.CommandResult;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
