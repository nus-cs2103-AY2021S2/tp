package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.PredicateUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

public class InventoryFindCommand extends Command {

    public static final String COMPONENT_WORD = "inventory";
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds ingredients. "
            + "At least one prefix must be used. \n"
            + "n/ - Look for names that contain any of the keywords (case-insensitive) \n"
            + "q/ - Look for ingredients that have less than specified quantity \n"
            + "Parameters: n/[KEYWORD] (MORE_KEYWORDS)... q/[LESS THAN QUANTITY]\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " n/tomato q/4";

    private final Predicate<Ingredient> predicate;

    /**
     * Construct command with a list of predicates to use
     * @param predicates list of predicates to use
     */
    public InventoryFindCommand(List<Predicate<Ingredient>> predicates) {
        assert predicates != null && predicates.size() > 0;
        this.predicate = PredicateUtil.composePredicates(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW,
                        model.getFilteredIngredientList().size(),
                        Messages.ITEM_INGREDIENTS),
                CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryFindCommand // instanceof handles nulls
                && predicate.equals(((InventoryFindCommand) other).predicate)); // state check
    }
}
