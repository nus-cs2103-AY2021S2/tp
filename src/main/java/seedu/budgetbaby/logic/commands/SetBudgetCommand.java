package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.Budget;
import seedu.budgetbaby.model.BudgetBabyModel;

/**
 * Sets a budget for the following months.
 */
public class SetBudgetCommand extends BudgetBabyCommand {
    public static final String COMMAND_WORD = "set-bg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the budget for the following months.\n"
            + "Parameters: BG_AMOUNT\n"
            + "Note the BG_AMOUNT must be a positive number up to two decimal places.";
    public static final String MESSAGE_SUCCESS = "New budget set: %s";

    public static final String MESSAGE_FAILURE = "The budget amount set must be positive up to two decimal places.";

    private final Budget toSet;

    /**
     * Creates a SetBudgetCommand to set the specified {@code Budget}
     *
     * @param budget The specified Budget.
     */
    public SetBudgetCommand(Budget budget) {
        requireNonNull(budget);
        toSet = budget;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        if (toSet.getAmount() <= 0) {
            return new CommandResult(MESSAGE_FAILURE, false, false);
        }
        model.setBudget(toSet);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toSet), false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SetBudgetCommand // instanceof handles nulls
            && toSet.equals(((SetBudgetCommand) other).toSet));
    }
}
