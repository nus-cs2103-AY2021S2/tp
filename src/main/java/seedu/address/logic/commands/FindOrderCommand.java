package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COMPLETION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Finds the orders in CHIM matching the input parameters and lists them to the user.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find orders matching input completion status and cheese type(s).\n"
            + "Parameters: "
            + "[" + PREFIX_ORDER_COMPLETION_STATUS + "ORDER COMPLETION STATUS] "
            + "[" + PREFIX_CHEESE_TYPE + "CHEESE TYPE] "
            + "[" + PREFIX_NAME + "CUSTOMER NAME] "
            + "[" + PREFIX_PHONE + "CUSTOMER PHONE] \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_ORDER_COMPLETION_STATUS + "complete "
            + PREFIX_CHEESE_TYPE + "gouda "
            + PREFIX_NAME + "alice "
            + PREFIX_PHONE + "92280919";

    public final FieldPredicate<Order> predicate;

    /**
     * Creates a new {@code FindOrderCommand}.
     *
     * @param predicate Predicate for filtering the order list.
     */
    public FindOrderCommand(FieldPredicate<Order> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        model.setPanelToOrderList();
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindOrderCommand
                && predicate.equals(((FindOrderCommand) other).predicate));
    }
}
