package seedu.budgetbaby.logic.commands;

import java.util.logging.Logger;
import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.record.Category;

/**
 * Filters financial records by category.
 */
public class CategoryFilterCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "category-filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters financial records by category. "
            + "Parameters: "
            + PREFIX_CATEGORY + "FR_CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "Food ";

    public static final String MESSAGE_SUCCESS = "Financial Records list is now filtered to %1$s";

    private final Category category;

    /**
     * Creates a CategoryFrCommand to update the FR list
     */
    public CategoryFilterCommand(Category category) {
        requireNonNull(category);
        this.category = category;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        model.filterByCategory(category);
        return new CommandResult(String.format(MESSAGE_SUCCESS, category));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategoryFilterCommand // instanceof handles nulls
                && category.equals(((CategoryFilterCommand) other).category));
    }
}
