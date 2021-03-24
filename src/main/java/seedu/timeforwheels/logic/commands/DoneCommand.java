package seedu.timeforwheels.logic.commands;

import static seedu.timeforwheels.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.timeforwheels.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.List;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.commons.core.index.Index;
import seedu.timeforwheels.logic.commands.exceptions.CommandException;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.Done;

/**
 * Edits the details of an existing customer in the delivery list.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done with a tick "
            + "by the index number used in the listing. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DONE_CUSTOMER_SUCCESS =
        "The following Delivery Order has been checkmarked : %1$s";
    public static final String CHECKMARK = "[✓]";
    private final Index targetIndex;
    private final Done done;

    /**
     * @param targetIndex of the customer in the filtered customer list to edit
     */
    public DoneCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.done = new Done(CHECKMARK);

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Customer> lastShownList = model.getFilteredCustomerList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
        Customer customerToEdit = lastShownList.get(targetIndex.getZeroBased());

        Customer editedCustomer;

        if (customerToEdit.getDone().toString().equals("")) {
            editedCustomer = new Customer(customerToEdit.getName(), customerToEdit.getPhone(),
                    customerToEdit.getEmail(), customerToEdit.getAddress(),
                    customerToEdit.getRemark(), customerToEdit.getDate(),
                    customerToEdit.getTags(), done);

        } else {
            editedCustomer = new Customer(customerToEdit.getName(), customerToEdit.getPhone(),
                    customerToEdit.getEmail(), customerToEdit.getAddress(),
                    customerToEdit.getRemark(), customerToEdit.getDate(),
                    customerToEdit.getTags(), new Done(""));

        }

        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_DONE_CUSTOMER_SUCCESS, customerToEdit));
    }
}

