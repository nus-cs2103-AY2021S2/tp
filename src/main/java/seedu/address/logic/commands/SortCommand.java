package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
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
    public static final String OPTION_ALPHABETICAL = "name";
    public static final String OPTION_CHRONOLOGICAL = "date";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of persons in the address book.\n"
            + "Parameters: " + PREFIX_OPTION + "OPTION\n"
            + "Options: name (for alphabetical order) or date (for chronological order)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_ALPHABETICAL + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_CHRONOLOGICAL;
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

        if (order.equals(OPTION_ALPHABETICAL)) {
            sortedPersons = lastShownList.sorted();
            message = MESSAGE_SORT_ALPHABETICAL_SUCCESS;
        } else {    // order.equals(OPTION_CHRONOLOGICAL)
            sortedPersons = lastShownList.sorted(new DateComparator());
            message = MESSAGE_SORT_CHRONOLOGICAL_SUCCESS;
        }
        model.setPersons(sortedPersons);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
