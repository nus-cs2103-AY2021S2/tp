package chim.logic.commands;

import static chim.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static java.util.Objects.requireNonNull;

import chim.model.Model;

/**
 * Lists all customers in CHIM to the user.
 */
public class ListCustomersCommand extends Command {

    public static final String COMMAND_WORD = "listcustomers";
    public static final String MESSAGE_SUCCESS = "Listed all customers";
    public static final String SUMMARY_MESSAGE = "Listed %d customers";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        model.setPanelToCustomerList();
        return new CommandResult(
                String.format(
                        SUMMARY_MESSAGE,
                        model.getFilteredCustomerList().size()
                )
        );
    }
}
