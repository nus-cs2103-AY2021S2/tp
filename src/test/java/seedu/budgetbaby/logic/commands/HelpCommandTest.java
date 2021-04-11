package seedu.budgetbaby.logic.commands;

import static seedu.budgetbaby.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.budgetbaby.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;


public class HelpCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager();

    private BudgetBabyModel expectedModel = new BudgetBabyModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
