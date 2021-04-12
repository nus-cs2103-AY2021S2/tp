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

    public static final String COMPONENT_WORD = "order";
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds orders. "
            + "At least one prefix must be used. \n"
            + "n/ - Look for customer names that contain any of the keywords (case-insensitive) \n"
            + "d/ - Look for dish names that contain keyword (case-insensitive) \n"
            + "Parameters: n/[KEYWORD] (MORE_KEYWORDS)... d/[KEYWORD]\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " n/Alex Sally d/Burger";

    private final Predicate<Order> predicate;

    /**
     * Constructs order command based on a list of predicates
     * @param predicates
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
                        model.getFilteredOrderList().size(),
                        Messages.ITEM_ORDERS),
                CommandResult.CRtype.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderFindCommand // instanceof handles nulls
                && predicate.equals(((OrderFindCommand) other).predicate)); // state check
    }
}
