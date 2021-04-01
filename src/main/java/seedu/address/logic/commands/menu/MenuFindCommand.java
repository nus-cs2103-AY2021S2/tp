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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds dishes. "
            + "At least one prefix must be used. \n"
            + "n/ - Look for names that contain any of the keywords (case-insensitive) \n"
            + "i/ - Look for ingredients that contains a keyword (case-insensitive) \n"
            + "Parameters: n/[KEYWORD] (MORE_KEYWORDS)... i/[KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " n/burger steak i/beef";

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
                        model.getFilteredDishList().size(),
                        Messages.ITEM_DISHES),
                CommandResult.CRtype.DISH);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuFindCommand // instanceof handles nulls
                && predicate.equals(((MenuFindCommand) other).predicate)); // state check
    }
}
