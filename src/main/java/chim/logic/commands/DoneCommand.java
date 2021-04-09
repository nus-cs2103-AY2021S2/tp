package chim.logic.commands;

import static chim.model.AbstractDate.TO_JSON_STRING_FORMATTER;
import static chim.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import chim.commons.core.Messages;
import chim.commons.core.index.Index;
import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;
import chim.model.cheese.CheeseId;
import chim.model.order.CompletedDate;
import chim.model.order.Order;
import chim.model.order.Quantity;

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
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToUpdate = lastShownList.get(targetIndex.getZeroBased());
        if (orderToUpdate.isComplete()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_COMPLETE);
        }

        Set<CheeseId> unassignedCheeses = model.getUnassignedCheeses(orderToUpdate.getCheeseType(),
            orderToUpdate.getQuantity());
        Quantity expectedQuantity = orderToUpdate.getQuantity();
        if (!expectedQuantity.isSameQuantity(unassignedCheeses.size())) {
            throw new CommandException(Messages.MESSAGE_INSUFFICIENT_CHEESES_FOR_ORDER);
        }

        Order updatedOrder = createDoneOrder(orderToUpdate, unassignedCheeses, model);
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
    public static Order createDoneOrder(Order orderToUpdate, Set<CheeseId> unassignedCheeses, Model model)
        throws CommandException {
        CompletedDate completedDate = new CompletedDate(LocalDateTime.now().format(TO_JSON_STRING_FORMATTER));

        Order retOrder;

        try {
            retOrder = new Order(orderToUpdate, completedDate, unassignedCheeses);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        return retOrder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
