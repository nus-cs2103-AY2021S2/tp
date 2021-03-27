package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;

/**
 * Finds financial records by description, amount and/or category.
 */
public class FindFrCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "find-fr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds financial records"
            + "by description, amount and/or category.\n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "FR_DESCRIPTION "
            + PREFIX_AMOUNT + "FR_AMOUNT "
            + PREFIX_CATEGORY + "FR_CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Shoes "
            + PREFIX_AMOUNT + "40 "
            + PREFIX_CATEGORY + "Apparel";

    public static final String MESSAGE_SUCCESS = "Financial Records list is now filtered to %1$s."
            + "Use reset-filter to revert to original list.";

    private final Description description;
    private final Amount amount;
    private final Category category;

    /**
     * Creates a FindFrCommand to update the FR list
     */
    public FindFrCommand(Description description, Amount amount, Category category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        model.findFinancialRecord(description, amount, category);
        return new CommandResult(String.format(MESSAGE_SUCCESS, description, amount, category));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindFrCommand // instanceof handles nulls
                && category.equals(((FindFrCommand) other).category));
    }
}
