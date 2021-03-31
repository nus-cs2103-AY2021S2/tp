package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.core.Messages;
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
            + "Parameters: " + PREFIX_PHONE + "PHONE (must be a valid phone number)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + "61234567";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer: %1$s";

    private final Phone targetPhone;

    public DeleteCustomerCommand(Phone targetPhone) {
        this.targetPhone = targetPhone;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Customer customerToDelete = model.getCustomerWithPhone(targetPhone);
        if (customerToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_PHONE);
        }

        model.deleteCustomer(customerToDelete);
        model.setPanelToCustomerList(); // Display customer list

        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetPhone.equals(((DeleteCustomerCommand) other).targetPhone)); // state check
    }
}
