package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.LogicManager;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.order.DeliveryStatus;
import seedu.cakecollate.model.order.Order;

public class DeliveryStatusCommand extends Command {

    public static final String UNDELIVERED_COMMAND_WORD = "undelivered";

    public static final String DELIVERED_COMMAND_WORD = "delivered";

    public static final String CANCELLED_COMMAND_WORD = "cancelled";

    public static final String MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_UPDATED = "Updated order status for: %1$s";

    public static final String MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME =
            "The order status has already been updated for: %1$s";

    private final IndexList targetIndexList;

    private final DeliveryStatus status;
    final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Initialises a delivery status command.
     * @param targetIndexList IndexList of the indices that need to be updated.
     * @param status The status the deliveryStatus has to be changed to.
     */
    public DeliveryStatusCommand(IndexList targetIndexList, DeliveryStatus status) {
        this.targetIndexList = targetIndexList;
        this.status = status;
    }

    /** Returns the description of how each of the commands in DeliveryStatusCommand works. */
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

    public static String getResultString(List<Order> ordersToUpdate, List<Order> sameOrders) {
        String convertedToStringUpdated = "";
        for (Order order : ordersToUpdate) {
            convertedToStringUpdated = convertedToStringUpdated + String.format("\n%1$s", order);
        }

        String convertedToStringSame = "";
        for (Order order : sameOrders) {
            convertedToStringSame = convertedToStringSame + String.format("\n%1$s", order);
        }

        String output = "";
        if (!convertedToStringUpdated.equals("")) {
            output += String.format(MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_UPDATED, convertedToStringUpdated);
        }

        if (!convertedToStringUpdated.equals("") && !convertedToStringSame.equals("")) {
            output += "\n\n";
        }

        if (!convertedToStringSame.equals("")) {
            output += String.format(MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME, convertedToStringSame);
        }

        return output;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        List<Order> copy = new ArrayList<>(lastShownList);
        
        List<Order> updatedOrders = new ArrayList<>();
        List<Order> sameOrders = new ArrayList<>();

        for (Index targetIndex:this.targetIndexList.getIndexList()) {
            if (targetIndex.getZeroBased() >= copy.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
            }

            Order orderToUpdate = copy.get(targetIndex.getZeroBased());

            if (orderToUpdate.getDeliveryStatus().equals(status)) {
                sameOrders.add(orderToUpdate);
            } else {
                Order editedOrder = updateOrder(orderToUpdate, status);
                model.setOrder(orderToUpdate, editedOrder);
                model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
                updatedOrders.add(editedOrder);
            }
        }
        return new CommandResult(getResultString(updatedOrders, sameOrders));
    }

    private static Order updateOrder(Order order, DeliveryStatus status) {
        return new Order(order.getName(), order.getPhone(), order.getEmail(), order.getAddress(),
                order.getOrderDescriptions(), order.getTags(), order.getDeliveryDate(), status, order.getRequest());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryStatusCommand // instanceof handles nulls
                && targetIndexList.equals(((DeliveryStatusCommand) other).targetIndexList)
                && status.equals(((DeliveryStatusCommand) other).status)); // state check
    }
}
