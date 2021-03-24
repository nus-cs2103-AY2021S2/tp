package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.logic.BudgetBabyLogicManager;
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

    private final Logger logger = LogsCenter.getLogger(BudgetBabyLogicManager.class);

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        model.resetFilter();
        logger.info("Executing reset...");
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}

