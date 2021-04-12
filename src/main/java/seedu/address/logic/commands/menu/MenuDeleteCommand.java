package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.order.Order;

/**
 * Deletes a dish identified using it's displayed index from the address book.
 */
public class MenuDeleteCommand extends Command {

    public static final String COMPONENT_WORD = "menu";
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Deletes the dish identified by the index number used in the displayed menu.\n"
            + "Parameters: [INDEX] (-f)\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DISH_SUCCESS = "Deleted dish: %1$s";
    public static final String MESSAGE_DELETE_DISH_FAILURE =
            "Failed to delete dish: %1$s due to outstanding orders, "
            + "add -f flag to force delete the dish\n"
            + "Warning: This will mark any order that contains %1$s as 'Cancelled'";

    private final Index targetIndex;
    private final boolean isForce;

    public MenuDeleteCommand(Index targetIndex) {
        this(targetIndex, false);
    }

    /**
     * Delete dish given index and isForce flag
     * @param targetIndex index of dish to be deleted
     * @param isForce required to be true if there are outstanding orders that will also be cancelled
     */
    public MenuDeleteCommand(Index targetIndex, boolean isForce) {
        this.targetIndex = targetIndex;
        this.isForce = isForce;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Dish> lastShownList = model.getFilteredDishList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_DISH));
        }

        Dish dishToDelete = lastShownList.get(targetIndex.getZeroBased());

        List<Order> outstandingOrders = model.getIncompleteOrdersContainingDish(dishToDelete);
        boolean isOutstandingOrders = !outstandingOrders.isEmpty();

        if (isOutstandingOrders && !isForce) {
            throw new CommandException(String.format(MESSAGE_DELETE_DISH_FAILURE, dishToDelete.getName()));
        }

        model.cancelOrders(outstandingOrders);
        for (Order o : outstandingOrders) {
            System.out.println(o);
        }
        model.deleteDish(dishToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_DISH_SUCCESS, dishToDelete.getName()),
                CommandResult.CRtype.DISH);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((MenuDeleteCommand) other).targetIndex)); // state check
    }
}
