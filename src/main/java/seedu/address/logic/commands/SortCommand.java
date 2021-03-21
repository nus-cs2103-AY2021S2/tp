package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.sql.Timestamp;
import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
/**
 * Sorts the list of persons in the address book in alphabetical order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String ORDER_ALPHABETICAL = "name";
    public static final String ORDER_CHRONOLOGICAL = "date";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of persons in the address book.\n"
            + "Parameters: name (for alphabetical order) or date (for chronological order)\n"
            + "Example: " + COMMAND_WORD + " " + ORDER_ALPHABETICAL + "\n"
            + "Example: " + COMMAND_WORD + " " + ORDER_CHRONOLOGICAL;
    public static final String MESSAGE_SORT_ALPHABETICAL_SUCCESS = "List has been sorted in alphabetical order.";
    public static final String MESSAGE_SORT_CHRONOLOGICAL_SUCCESS = "List has been sorted in chronological order.";
    public static final String MESSAGE_SORT_FAILURE = "Sorting FAILED."
            + "Please double check usage as follows.\n" + MESSAGE_USAGE;

    private final String order;

    public SortCommand(String order) {
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Person> lastShownList = model.getFilteredPersonList();
        SortedList<Person> sortedPersons;
        String message;

        if (order.equals(ORDER_ALPHABETICAL)) {
            sortedPersons = lastShownList.sorted();
            model.setPersons(sortedPersons);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            message = MESSAGE_SORT_ALPHABETICAL_SUCCESS;
        } else if (order.equals(ORDER_CHRONOLOGICAL)) {
            sortedPersons = lastShownList.sorted(new DateComparator());
            model.setPersons(sortedPersons);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            message = MESSAGE_SORT_CHRONOLOGICAL_SUCCESS;
        } else {
            message = MESSAGE_SORT_FAILURE;
        }
        return new CommandResult(message);
    }

    class DateComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            Timestamp t1 = p1.getTimeAdded().getTimestamp();
            Timestamp t2 = p2.getTimeAdded().getTimestamp();

            if (t1.before(t2)) {
                return -1;
            } else if (t1.after(t2)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
