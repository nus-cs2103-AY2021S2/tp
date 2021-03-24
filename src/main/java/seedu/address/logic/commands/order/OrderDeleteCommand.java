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
public class OrderDeleteCommand extends Command {

    public static final String COMPONENT_WORD = "order";
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted order: %1$s";

    private final Index targetIndex;

    public OrderDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOrder(orderToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete),
                CommandResult.CRtype.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((OrderDeleteCommand) other).targetIndex)); // state check
    }
}
