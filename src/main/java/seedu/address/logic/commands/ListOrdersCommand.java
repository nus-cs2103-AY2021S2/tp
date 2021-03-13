package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import seedu.address.model.Model;

/**
 * Lists all orders in the address book to the user.
 */
public class ListOrdersCommand extends Command {

    public static final String COMMAND_WORD = "listorders";

    public static final String MESSAGE_SUCCESS = "Listed all orders";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Need to filter to show all customers, because the full list of customers is needed when
        // adding details to the orders' UI cards.
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);

        model.setPanelToOrderList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
