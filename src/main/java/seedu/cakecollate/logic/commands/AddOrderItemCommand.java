package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.orderitem.OrderItem;


/**
 * Adds an order item to the list of Order Items.
 */
public class AddOrderItemCommand extends Command {

    public static final String COMMAND_WORD = "addItem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order item to the list of order items. \n"
            + "Parameters: " + "ORDER_ITEM_DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " Chocolate Cake";

    public static final String MESSAGE_SUCCESS = "New order item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER_ITEM = "This order item already exists in the list.";

    private final OrderItem orderItem;


    /**
     * Adds an order item to the list of order items upon execution.
     *
     * @param orderItem Order Item to be added
     */
    public AddOrderItemCommand(OrderItem orderItem) {
        requireNonNull(orderItem);
        this.orderItem = orderItem;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        OrderItem toAdd = orderItem; // slightly diff from editOrderDescriptor

        if (model.hasOrderItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER_ITEM);
        }

        model.addOrderItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrderItemCommand)) {
            return false;
        }

        // state check
        AddOrderItemCommand a = (AddOrderItemCommand) other;
        return (Objects.equals(orderItem, a.orderItem));
    }
}
