package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import seedu.address.model.Model;

/**
 * Lists all customers in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Great! List of all customers generated ";

    public static final String MESSAGE_EMPTY_CUSTOMER_LIST = "Oh no! There is no customers to list, "
            + "try adding some customers";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        if (model.getFilteredCustomerList().size() == 0) {
            return new CommandResult(MESSAGE_EMPTY_CUSTOMER_LIST);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
