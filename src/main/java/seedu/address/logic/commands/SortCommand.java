package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sort and list the clients in the ClientBook by name to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS_ASCENDING = "Sorted all clients in ascending order.";

    public static final String MESSAGE_SUCCESS_DESCENDING = "Sorted all clients in descending order.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all clients by names in the order of "
            + "the specified direction and displays them as a list with index numbers.\n"
            + "Parameters: DIRECTION (/a for ascending, /d for descending)\n"
            + "Example: " + COMMAND_WORD + " /a";;

    private final String direction;

    public SortCommand(String direction) {
        this.direction = direction;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Comparator<Person> comparator = new PersonNameComparator();
        if (direction.equals("/d")) {
            comparator = comparator.reversed();
        }
        model.updateSortedPersonList(comparator);
        if (direction.equals("/a")) {
            return new CommandResult(MESSAGE_SUCCESS_ASCENDING);
        } else {
            return new CommandResult(MESSAGE_SUCCESS_DESCENDING);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
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
}
