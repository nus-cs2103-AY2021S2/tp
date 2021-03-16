package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AbstractDate.TO_JSON_STRING_FORMATTER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.Quantity;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks order as complete"
            + "by the index number used in the displayed order list. "
            + "Parameters : INDEX (must be a positive integer) ";

    public static final String MESSAGE_MARK_ORDER_DONE_SUCCESS = "ORDER : %1$s completed";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_INDEX);
        }

        Order orderToUpdate = lastShownList.get(targetIndex.getZeroBased());
        if (orderToUpdate.isComplete()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_COMPLETE);
        }

        Order updatedOrder = createDoneOrder(orderToUpdate, model);
        Quantity expectedQuantity = orderToUpdate.getQuantity();
        int numAssignedCheeses = updatedOrder.getCheeses().size();
        if (!expectedQuantity.isSameQuantity(numAssignedCheeses)) {
            throw new CommandException(Messages.MESSAGE_INSUFFICIENT_CHEESES_FOR_ORDER);
        }
        model.setOrder(orderToUpdate, updatedOrder);
        model.updateCheesesStatus(updatedOrder.getCheeses());
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        model.setPanelToOrderList();
        return new CommandResult(String.format(MESSAGE_MARK_ORDER_DONE_SUCCESS, updatedOrder));
    }

    /**
     * Creates and returns a {@code order} with the details of {@code orderToUpdate}
     * Only the CompletedDate is updated to current time
     */
    public static Order createDoneOrder(Order orderToUpdate, Model model) {
        CompletedDate completedDate = new CompletedDate(LocalDateTime.now().format(TO_JSON_STRING_FORMATTER));
        Set<CheeseId> getUnassignedCheeses = model.getUnassignedCheeses(orderToUpdate.getCheeseType(),
                orderToUpdate.getQuantity());
        return new Order(orderToUpdate, completedDate, getUnassignedCheeses);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
