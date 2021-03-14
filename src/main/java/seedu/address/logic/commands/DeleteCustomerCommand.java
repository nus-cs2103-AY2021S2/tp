package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;

/**
 * Deletes a customer identified using it's displayed index from the customer list.
 */
public class DeleteCustomerCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deletecustomer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the phone used in the displayed customer list.\n"
            + "Parameters: PHONE (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 61234567";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer: %1$s";

    private final Phone targetPhone;

    public DeleteCustomerCommand(Phone targetPhone) {
        this.targetPhone = targetPhone;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        Index targetIndex = Index.fromZeroBased(lastShownList.size() + 1);

        // Gets index of customer with the provided phone number
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getPhone() == targetPhone) {
                targetIndex = Index.fromZeroBased(i);
                break;
            }
        }

        if (targetIndex.getZeroBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_PHONE);
        }

        Customer customerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCustomer(customerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetPhone.equals(((DeleteCustomerCommand) other).targetPhone)); // state check
    }
}
