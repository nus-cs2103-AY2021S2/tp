package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Adds a financial record to the budget tracker.
 */
public class AddFrCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "add-fr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a financial record. "
        + "Parameters: "
        + PREFIX_DESCRIPTION + "FR_DESCRIPTION "
        + PREFIX_AMOUNT + "FR_AMOUNT "
        + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_DESCRIPTION + "Lunch "
        + PREFIX_AMOUNT + "10 "
        + PREFIX_CATEGORY + "Food";

    public static final String MESSAGE_SUCCESS = "New financial record added: %1$s";

    private final FinancialRecord toAdd;

    /**
     * Creates an AddFrCommand to add the specified {@code Person}
     */
    public AddFrCommand(FinancialRecord record) {
        requireNonNull(record);
        toAdd = record;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);

        model.addFinancialRecord(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddFrCommand // instanceof handles nulls
            && toAdd.equals(((AddFrCommand) other).toAdd));
    }
}
