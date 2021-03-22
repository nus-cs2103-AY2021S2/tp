package seedu.cakecollate.logic.commands;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.order.DeliveryStatus;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.Status;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.logic.parser.CliSyntax.*;
import static seedu.cakecollate.model.Model.PREDICATE_SHOW_ALL_ORDERS;

public class DeliveryStatusCommand extends Command {

    public static final String UNDELIVERED_COMMAND_WORD = "undelivered";

    public static final String DELIVERED_COMMAND_WORD = "delivered";

    public static final String CANCELLED_COMMAND_WORD = "cancelled";

    public static String messageUsage(String commandWord) {
        return commandWord
                + ": Updates the deliveryStatus of the order identified by the index number "
                + "used in the displayed order list to " + commandWord + ".\n"
                + "Parameters: INDEX (must be a positive integer)\n"
                + "Example: " + commandWord + " 1 2 3";
    }

    public static String getMessageUsage(String commandWord) {
        return messageUsage(commandWord.toLowerCase());
    }

    public static final String MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS = "Updated order status for: %1$s";

    private final IndexList targetIndexList;

    private final DeliveryStatus status;

    public DeliveryStatusCommand(IndexList targetIndexList, DeliveryStatus status) {
        this.targetIndexList = targetIndexList;
        this.status = status;
    }

    public static String getResultString(List<Order> ordersToUpdate) {
        String convertedToString = "";
        for (Order order : ordersToUpdate) {
            convertedToString = convertedToString + String.format("\n%1$s", order);
        }
        return String.format(MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS, convertedToString);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();
        List<Order> updatedOrders = new ArrayList<>();
        for (Index targetIndex:this.targetIndexList.getIndexList()) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
            }
            Order orderToUpdate = lastShownList.get(targetIndex.getZeroBased());
            Order editedOrder = updateOrder(orderToUpdate, status);
            model.setOrder(orderToUpdate, editedOrder);
            model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
            updatedOrders.add(orderToUpdate);
        }
        return new CommandResult(getResultString(updatedOrders));
    }

    private static Order updateOrder(Order order, DeliveryStatus status) {
        return new Order(order.getName(), order.getPhone(), order.getEmail(), order.getAddress(),
                order.getOrderDescriptions(), order.getTags(), order.getDeliveryDate(), status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryStatusCommand // instanceof handles nulls
                && targetIndexList.equals(((DeliveryStatusCommand) other).targetIndexList)); // state check
    }
}
