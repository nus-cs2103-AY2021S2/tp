package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Done;

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


    public static final String MESSAGE_DONE_CUSTOMER_SUCCESS = "Checkmarked Delivery Order: %1$s";
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
                    customerToEdit.getRemark(), customerToEdit.getTags(), done);

        } else {
            editedCustomer = new Customer(customerToEdit.getName(), customerToEdit.getPhone(),
                    customerToEdit.getEmail(), customerToEdit.getAddress(),
                    customerToEdit.getRemark(), customerToEdit.getTags(), new Done(""));

        }

        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_DONE_CUSTOMER_SUCCESS, customerToEdit));
    }



}

