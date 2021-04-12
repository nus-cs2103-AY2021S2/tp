package seedu.budgetbaby.model.month.exception;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;

public class TotalExpenseOutOfBoundException extends CommandException {
    public TotalExpenseOutOfBoundException() {
        super("Action denied! Your current action will cause your total monthly expense to exceed the maximum " +
            "monthly expense allowed which is $1,000,000.");
    }
}
