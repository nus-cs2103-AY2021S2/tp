package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class OrderCompleteCommand extends Command {

    public static final String COMPONENT_WORD = "order";
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Completes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: [INDEX]\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COMPLETE_ORDER_SUCCESS = "Completed order: %1$s";

    private final Index targetIndex;

    public OrderCompleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredOrderList(order -> order.getState() == Order.State.UNCOMPLETED);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_ORDER));
        }

        Order orderToComplete = lastShownList.get(targetIndex.getZeroBased());
        Order completedOrder = orderToComplete.setState(Order.State.COMPLETED);
        model.setOrder(orderToComplete, completedOrder);
        model.updateFilteredOrderList(order -> order.getState() == Order.State.UNCOMPLETED);
        return new CommandResult(String.format(MESSAGE_COMPLETE_ORDER_SUCCESS, orderToComplete),
                CommandResult.CRtype.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderCompleteCommand // instanceof handles nulls
                && targetIndex.equals(((OrderCompleteCommand) other).targetIndex)); // state check
    }
}
