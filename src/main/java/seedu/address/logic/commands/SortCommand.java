package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.person.Person;

/**
 * Sorts the current list of clients in the ClientBook.
 * Attribute and direction of the sorting need to be specified.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String SORT_BY_NAME = "-n";
    public static final String SORT_BY_POLICY = "-p";

    public static final String DIRECTION_ASCENDING = "-a";
    public static final String DIRECTION_DESCENDING = "-d";

    public static final String MESSAGE_SUCCESS_ASCENDING = "Sorted all clients in ascending order.";
    public static final String MESSAGE_SUCCESS_DESCENDING = "Sorted all clients in descending order.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of clients by "
            + "the specified property in the order of the specified direction.\n"
            + "Parameters: ATTRIBUTE (-n name, -p policy) DIRECTION (-a ascending, -d descending)\n"
            + "Example: " + COMMAND_WORD + " -n -a";

    private final String attribute;
    private final String direction;

    /**
     * @param attribute of the client to be sorted with
     * @param direction of the sorting order
     */
    public SortCommand(String attribute, String direction) {
        this.attribute = attribute;
        this.direction = direction;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Comparator<Person> comparator;

        if (attribute.equals(SORT_BY_POLICY)) {
            comparator = new PolicyComparator();
        } else {
            comparator = new PersonNameComparator();
        }

        if (direction.equals(DIRECTION_DESCENDING)) {
            comparator = comparator.reversed();
        }
        model.updateSortedPersonList(comparator);

        if (direction.equals(DIRECTION_ASCENDING)) {
            return new CommandResult(MESSAGE_SUCCESS_ASCENDING);
        } else {
            return new CommandResult(MESSAGE_SUCCESS_DESCENDING);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attribute.equals(((SortCommand) other).attribute) // state check
                && direction.equals(((SortCommand) other).direction)); // state check
    }

    /**
     * Comparator to compare the names of clients.
     */
    public static class PersonNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person person, Person otherPerson) {
            return (person.getName().toString()).compareTo(otherPerson.getName().toString());
        }
    }

    /**
     * Comparator to compare the policies of clients.
     */
    public static class PolicyComparator implements Comparator<Person> {
        @Override
        public int compare(Person person, Person otherPerson) {
            List<InsurancePolicy> policy = person.getPolicies();
            List<InsurancePolicy> otherPolicy = otherPerson.getPolicies();

            if (policy.size() > otherPolicy.size()) {
                return 1;
            } else if (policy.size() < otherPolicy.size()) {
                return -1;
            } else {
                return (policy.toString()).compareTo(otherPolicy.toString());
            }
        }
    }
}
