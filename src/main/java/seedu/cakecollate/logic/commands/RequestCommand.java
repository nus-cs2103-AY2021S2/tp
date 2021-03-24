package seedu.cakecollate.logic.commands;

import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.Request;

/**
 * Command to add a request field to the order.
 * Request field is initially empty when an order is added.
 */
public class RequestCommand extends Command {

    public static final String COMMAND_WORD = "request";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the request of the order identified "
            + "by the index number used in the last order listing. "
            + "Existing request will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REQUEST + "[REQUEST]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REQUEST + "No vanilla in the Vanilla Cake.\n"
            + "Empty requests would change the request status back to -No special requests added.";

    public static final String MESSAGE_ADD_REQUEST_SUCCESS = "Added request to order: %1$s";
    public static final String MESSAGE_DELETE_REQUEST_SUCCESS = "Removed request from order: %1$s";

    private final Index index;
    private final Request request;

    /**
     * @param index of the order in the filtered order list to edit the order
     * @param request of the order to be updated to
     */
    public RequestCommand(Index index, Request request) {
        requireAllNonNull(index, request);

        this.index = index;
        this.request = request;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Order editedOrder = new Order(orderToEdit.getName(), orderToEdit.getPhone(), orderToEdit.getEmail(),
                orderToEdit.getAddress(), orderToEdit.getOrderDescriptions(),
                orderToEdit.getTags(), orderToEdit.getDeliveryDate(), request);

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);

        return new CommandResult(generateSuccessMessage(editedOrder));
    }

    /**
     * Generates a command execution success message based on whether the request is added to or removed from
     * {@code orderToEdit}.
     */
    private String generateSuccessMessage(Order orderToEdit) {
        boolean isEmptyRequest = request.isRequestEmpty();
        String message = isEmptyRequest
                ? MESSAGE_DELETE_REQUEST_SUCCESS
                : MESSAGE_ADD_REQUEST_SUCCESS;
        return String.format(message, orderToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RequestCommand)) {
            return false;
        }

        // state check
        RequestCommand e = (RequestCommand) other;
        return index.equals(e.index)
                && request.equals(e.request);
    }
}
