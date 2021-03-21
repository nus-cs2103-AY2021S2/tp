package seedu.timeforwheels.logic.commands;

import static seedu.timeforwheels.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.timeforwheels.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.List;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.commons.core.index.Index;
import seedu.timeforwheels.logic.commands.exceptions.CommandException;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.Remark;



public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the customer identified "
            + "by the index number used in the last customer listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Customer: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Customer: %1$s";

    private final Index index;
    private final Remark remark;


    /**
     * @param index of the customer in the filtered customer list to edit the remark
     * @param remark of the customer to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);
        this.index = index;
        this.remark = remark;
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Customer editedCustomer = new Customer(customerToEdit.getName(), customerToEdit.getPhone(),
                customerToEdit.getEmail(), customerToEdit.getAddress(), remark,
                customerToEdit.getTags(), customerToEdit.getDone());
        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(generateSuccessMessage(editedCustomer));
    }

    private String generateSuccessMessage(Customer editedCustomer) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS
                : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, editedCustomer);
    }
}

