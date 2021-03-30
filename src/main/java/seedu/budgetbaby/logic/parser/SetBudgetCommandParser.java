package seedu.budgetbaby.logic.parser;

import seedu.budgetbaby.logic.commands.SetBudgetCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.budget.Budget;

/**
 * Parses input arguments and creates a new SetBudgetCommand object.
 */
public class SetBudgetCommandParser implements BudgetBabyCommandParser<SetBudgetCommand> {
    @Override
    public SetBudgetCommand parse(String args) throws ParseException {
        double amount = ParserUtil.parseBudgetAmount(args);
        Budget budget = new Budget(amount);
        return new SetBudgetCommand(budget);
    }
}
