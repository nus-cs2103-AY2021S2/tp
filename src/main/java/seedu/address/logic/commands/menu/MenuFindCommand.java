package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.PredicateUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;

public class MenuFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all ingredients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " tomato fish lemon";

    private final Predicate<Dish> predicate;

    /**
     * Construct command with a list of predicates to use
     * @param predicates list of predicates to use
     */
    public MenuFindCommand(List<Predicate<Dish>> predicates) {
        assert predicates != null && predicates.size() > 0;
        this.predicate = PredicateUtil.composePredicates(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDishList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW,
                        model.getFilteredIngredientList().size(),
                        Messages.ITEM_INGREDIENTS),
                CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuFindCommand // instanceof handles nulls
                && predicate.equals(((MenuFindCommand) other).predicate)); // state check
    }
}
