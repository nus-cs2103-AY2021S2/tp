package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.PredicateUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class OrderFindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all orders whose customer's names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alex bob";

    private final Predicate<Order> predicate;

    /**
     * Find orders based on the given predicates
     * @param predicates condition to find orders
     */
    public OrderFindCommand(List<Predicate<Order>> predicates) {
        assert predicates != null && predicates.size() > 0;
        this.predicate = PredicateUtil.composePredicates(predicates);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW,
                        model.getFilteredIngredientList().size(),
                        Messages.ITEM_INGREDIENTS),
                CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderFindCommand // instanceof handles nulls
                && predicate.equals(((OrderFindCommand) other).predicate)); // state check
    }
}
