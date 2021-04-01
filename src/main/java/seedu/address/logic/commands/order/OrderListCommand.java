package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.order.Order;


/**
 * Lists all orders to the user.
 */
public class OrderListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all orders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Comparator<Order> comparator = (first, second) ->
                first.getDatetime().isAfter(second.getDatetime()) ? 1 : 0;
        model.updateFilteredOrderList(comparator);
        model.updateFilteredOrderList(order -> order.getState() == Order.State.UNCOMPLETED);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CRtype.ORDER);
    }
}
