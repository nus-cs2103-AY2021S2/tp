package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CHEESES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.order.Order;

/**
 * Deletes an order identified using its displayed index from the order list.
 */
public class DeleteOrderCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deleteorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final Index targetIndex;

    public DeleteOrderCommand(Index targetIndex) {
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
        model.updateCheesesStatus(orderToDelete.getCheeses());

        // Find and delete any cheeses that the order has, by CheeseID
        if (orderToDelete.getCheeses().size() > 0) {
            model.getFilteredCheeseList();
            model.updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);
            int cheeseListSize = model.getFilteredCheeseList().size();

            ArrayList<CheeseId> cheeseIdToDelete = new ArrayList<>(orderToDelete.getCheeses());
            Collections.sort(cheeseIdToDelete);

            for (int i = cheeseIdToDelete.size() - 1; i >= 0; i--) {
                DeleteCheeseCommand toDeleteCheese = new DeleteCheeseCommand(cheeseIdToDelete.get(i), model);

                // Cheeses still in the cheese list
                if (toDeleteCheese.getTargetIndexValue() < cheeseListSize) {
                    toDeleteCheese.execute(model);
                }
            }
        }

        model.deleteOrder(orderToDelete);
        model.setPanelToOrderList(); // Display order list
        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrderCommand) other).targetIndex)); // state check
    }
}
