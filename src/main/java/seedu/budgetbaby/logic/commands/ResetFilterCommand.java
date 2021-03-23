package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;

/**
 * Filters financial records by category.
 */
public class ResetFilterCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "reset-filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets filters set to Financial Records. "
            + "No Parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Filters on Financial Records list have been reset";

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        model.resetFilter();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}

