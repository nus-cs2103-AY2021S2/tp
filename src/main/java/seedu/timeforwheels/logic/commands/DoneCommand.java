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
 * Marks a delivery task identified using it's displayed index from the delivery list as done [✓].
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set a delivery task in the delivery list to done or not done \n"
            + "Parameters: TASK_NUMBER \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DONE_CUSTOMER_SUCCESS =
        "The following Delivery Task has been marked : %1$s";
    public static final String CHECKMARK = "[✓]";
    public static final String CROSS = "[X]";
    private final Index targetIndex;
    private final Done done;

    /**
     * @param targetIndex of the delivery task in the filtered delivery list to mark as done
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

        if (!customerToEdit.getDone().toString().equals(CHECKMARK)) {
            editedCustomer = new Customer(customerToEdit.getName(), customerToEdit.getPhone(),
                    customerToEdit.getEmail(), customerToEdit.getAddress(),
                    customerToEdit.getRemark(), customerToEdit.getDate(),
                    customerToEdit.getTags(), done);

        } else {
            editedCustomer = new Customer(customerToEdit.getName(), customerToEdit.getPhone(),
                    customerToEdit.getEmail(), customerToEdit.getAddress(),
                    customerToEdit.getRemark(), customerToEdit.getDate(),
                    customerToEdit.getTags(), new Done(CROSS));

        }

        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_DONE_CUSTOMER_SUCCESS, customerToEdit));
    }
}

