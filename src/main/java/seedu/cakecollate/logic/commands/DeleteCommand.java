package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.order.Order;

/**
 * Deletes a order identified using it's displayed index from the cakecollate.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    public static final String MESSAGE_DELETE_ORDERS_SUCCESS = "Deleted Orders: %1$s";

    private final IndexList targetIndexList;

    public DeleteCommand(IndexList targetIndexList) {
        this.targetIndexList = targetIndexList;
    }

    public static String getResultString(List<Order> ordersToDelete) {
        if (ordersToDelete.size() == 1) {
            return String.format(MESSAGE_DELETE_ORDER_SUCCESS, ordersToDelete.get(0));
        }
        String convertedToString = "";
        for (Order order : ordersToDelete) {
            convertedToString = convertedToString + String.format("\n%1$s", order);
        }
        return String.format(MESSAGE_DELETE_ORDERS_SUCCESS, convertedToString);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();
        List<Order> ordersToDelete = new ArrayList<>();
        for (Index targetIndex:this.targetIndexList.getIndexList()) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
            }

            Order orderToDelete = lastShownList.get(targetIndex.getZeroBased());
            ordersToDelete.add(orderToDelete);
            model.deleteOrder(orderToDelete);
        }

        return new CommandResult(getResultString(ordersToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexList.equals(((DeleteCommand) other).targetIndexList)); // state check
    }
}
