package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import seedu.address.model.Model;

/**
 * Lists all orders in the address book to the user.
 */
public class ListOrdersCommand extends Command {

    public static final String COMMAND_WORD = "listorders";
    public static final String MESSAGE_SUCCESS = "Listed all orders";
    public static final String SUMMARY_MESSAGE = "Listed %d orders (%d completed, %d incomplete)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        model.setPanelToOrderList();
        int incompleteCount = model.getFilteredOrderListIncompleteCount();
        int totalOrder = model.getFilteredOrderList().size();
        return new CommandResult(
                String.format(
                        SUMMARY_MESSAGE,
                        model.getFilteredOrderList().size(),
                        totalOrder - incompleteCount,
                        incompleteCount)
        );
    }
}
