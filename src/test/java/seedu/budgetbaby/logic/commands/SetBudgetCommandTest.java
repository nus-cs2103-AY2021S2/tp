package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.Budget;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.UserPrefs;

public class SetBudgetCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());

    @Test
    public void execute_setBudget() throws CommandException {
        Budget budget = new Budget();
        budget.setAmount(500.00);
        SetBudgetCommand setBG = new SetBudgetCommand(budget);

        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        setBG.execute(expectedModel);
        String actualOutput = expectedModel.getFilteredMonthList().get(0).getBudget().toString();

        assertEquals("$500.00", actualOutput);
    }

    @Test
    public void execute_invalidBudget() throws CommandException {
        Budget budget = new Budget();
        budget.setAmount(-500.00);
        SetBudgetCommand setBG = new SetBudgetCommand(budget);

        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        String actualMessage = setBG.execute(expectedModel).getFeedbackToUser();
        String actualOutput = expectedModel.getFilteredMonthList().get(0).getBudget().toString();

        assertEquals("$1000.00", actualOutput);
        assertEquals("The budget amount set must be positive up to two decimal places.", actualMessage);
    }
}
