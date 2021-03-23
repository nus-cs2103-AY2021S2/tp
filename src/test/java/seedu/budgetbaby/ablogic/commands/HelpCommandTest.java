package seedu.budgetbaby.ablogic.commands;

import static seedu.budgetbaby.ablogic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.budgetbaby.ablogic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.abmodel.Model;
import seedu.budgetbaby.abmodel.ModelManager;
import seedu.budgetbaby.logic.commands.CommandResult;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, false, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
