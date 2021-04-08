package chim.logic.commands;

import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CliSyntax.PREFIX_NAME;
import static chim.logic.parser.CliSyntax.PREFIX_ORDER_COMPLETION_STATUS;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import chim.commons.core.Messages;
import chim.model.Model;
import chim.model.order.Order;
import chim.model.util.predicate.FieldPredicate;

/**
 * Finds the orders in CHIM matching the input keywords; keyword matching is case insensitive.
 * Lists the matching orders to the user.
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

        String message = String.format(
                Messages.MESSAGE_ORDERS_FOUND_OVERVIEW,
                model.getFilteredOrderList().size(),
                predicate
        );

        if (model.getFilteredOrderList().size() == 0) {
            message = String.format(Messages.MESSAGE_ORDERS_NOT_FOUND_OVERVIEW, predicate);
        }

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindOrderCommand
                && predicate.equals(((FindOrderCommand) other).predicate));
    }
}
