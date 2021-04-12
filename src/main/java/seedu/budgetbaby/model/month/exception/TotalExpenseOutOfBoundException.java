package seedu.budgetbaby.model.month.exception;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;

/**
 * Signals that the operation will result in total monthly expense to exceed the upper limit, 1,000,000.
 */
public class TotalExpenseOutOfBoundException extends CommandException {
    /**
     * Initialize a TotalExpenseOutOfBoundException
     */
    public TotalExpenseOutOfBoundException() {
        super("Action denied! Your current action will cause your total monthly expense to exceed the maximum "
            + "monthly expense allowed which is $1,000,000.");
    }
}
