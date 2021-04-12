package seedu.address.logic.commands.customer;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class CustomerDeleteCommand extends Command {

    public static final String COMPONENT_WORD = "customer";
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Deletes the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: [INDEX] (-f)\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted customer: %1$s";
    public static final String MESSAGE_DELETE_PERSON_FAILURE =
            "Failed to delete customer: %1$s due to outstanding orders, "
                    + "add -f flag to force delete the customer\n"
                    + "Warning: This will delete any order that contains %1$s";

    private final Index targetIndex;
    private final boolean isForce;

    public CustomerDeleteCommand(Index targetIndex) {
        this(targetIndex, false);
    }

    /**
     * Delete customer given index and isForce flag
     * @param targetIndex index of customer to be deleted
     * @param isForce required to be true if there are outstanding orders that will also be deleted
     */
    public CustomerDeleteCommand(Index targetIndex, boolean isForce) {
        this.targetIndex = targetIndex;
        this.isForce = isForce;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_PERSON));
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

        List<Order> outstandingOrders = model.getIncompleteOrdersFromPerson(personToDelete);
        boolean isOutstandingOrders = !outstandingOrders.isEmpty();

        if (isOutstandingOrders && !isForce) {
            throw new CommandException(String.format(MESSAGE_DELETE_PERSON_FAILURE, personToDelete.getName()));
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete.getName()),
                CommandResult.CRtype.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((CustomerDeleteCommand) other).targetIndex)); // state check
    }
}
