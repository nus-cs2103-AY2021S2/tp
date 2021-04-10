package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.commons.core.index.Index;
import seedu.timeforwheels.logic.commands.exceptions.CommandException;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;


/**
 * Deletes a customer identified using it's displayed index from the delivery list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "The following Customer has been deleted: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCustomer(customerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, customerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
