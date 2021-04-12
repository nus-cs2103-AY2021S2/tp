package seedu.address.logic.commands.customer;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class CustomerCommandUtil {
    public static final String MESSAGE_DUPLICATE_PERSON = "This customer already exists in the customer list";

    /**
     * Checks whether the Customer is a valid entry.
     * @param customer Candidate Customer to be added.
     * @param model The model object.
     * @return true if the Customer is a valid entry.
     * @throws CommandException If the Customer is an invalid entry.
     */
    public static boolean isValidCustomer(Person customer, Model model) throws CommandException {
        if (model.hasPerson(customer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        return true;
    }
}
