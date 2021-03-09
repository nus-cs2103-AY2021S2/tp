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

    public static final String MESSAGE_SUCCESS = "Sorted all clients";

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
        return new CommandResult(MESSAGE_SUCCESS + getDirection(direction));
    }

    private String getDirection(String direction) {
        if (direction.equals("/a")) {
            return " in ascending order.";
        } else {
            return " in descending order.";
        }
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
