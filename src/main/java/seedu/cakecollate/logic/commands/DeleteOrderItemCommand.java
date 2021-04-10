package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Deletes a order item identified using it's displayed index from the list of order items.
 */
public class DeleteOrderItemCommand extends Command {

    public static final String COMMAND_WORD = "deleteItem";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order items identified by the index number used in the displayed order item list.\n"
            + "Parameters: ORDER_ITEM_INDEXES (must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order Item: %1$s";

    public static final String MESSAGE_DELETE_ORDERS_SUCCESS = "Deleted Order Items: %1$s";

    private final IndexList targetIndexList;

    public DeleteOrderItemCommand(IndexList targetIndexList) {
        this.targetIndexList = targetIndexList;
    }

    public static String getResultString(List<OrderItem> orderItemsToDelete) {
        if (orderItemsToDelete.size() == 1) {
            return String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderItemsToDelete.get(0));
        }
        String convertedToString = "";
        for (OrderItem orderItem : orderItemsToDelete) {
            convertedToString = convertedToString + String.format("\n%1$s", orderItem);
        }
        return String.format(MESSAGE_DELETE_ORDERS_SUCCESS, convertedToString);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<OrderItem> lastShownList = model.getFilteredOrderItemsList();
        List<OrderItem> orderItemsToDelete = new ArrayList<>();
        for (Index targetIndex:this.targetIndexList.getIndexList()) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
            }
            OrderItem orderItemToDelete = lastShownList.get(targetIndex.getZeroBased());
            if (!orderItemsToDelete.contains(orderItemToDelete)) {
                orderItemsToDelete.add(orderItemToDelete);
            }
        }
        for (OrderItem orderItemToDelete:orderItemsToDelete) {
            model.deleteOrderItem(orderItemToDelete);
        }
        return new CommandResult(getResultString(orderItemsToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderItemCommand // instanceof handles nulls
                && targetIndexList.equals(((DeleteOrderItemCommand) other).targetIndexList)); // state check
    }
}
