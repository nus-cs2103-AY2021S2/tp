package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.budget.Budget;

public class SetBudgetCommand extends BudgetBabyCommand {
    public static final String COMMAND_WORD = "set-bg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the budget for the following months."
            + "Parameters: BG_AMOUNT";
    public static final String MESSAGE_SUCCESS = "New budget set: %s";

    private final Budget toSet;

    public SetBudgetCommand(Budget budget) {
        requireNonNull(budget);
        toSet = budget;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        model.setBudget(toSet);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toSet));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetBudgetCommand // instanceof handles nulls
                && toSet.equals(((SetBudgetCommand) other).toSet));
    }
}
