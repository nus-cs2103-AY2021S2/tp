package seedu.address.logic.commands.customer;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class CustomerCommandUtil {
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    /**
     * Checks whether the customer is a valid entry.
     * @param customer
     * @param model
     * @return
     * @throws CommandException
     */
    public static boolean isValidCustomer(Person customer, Model model) throws CommandException {
        if (model.hasPerson(customer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        return true;
    }
}
