package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.sql.Timestamp;
import java.util.Comparator;

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
            + "Options: name (for alphabetical order), date (for chronological order)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_ALPHABETICAL + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_CHRONOLOGICAL;
    public static final String MESSAGE_SORT_ALPHABETICAL_SUCCESS = "List has been sorted in alphabetical order.";
    public static final String MESSAGE_SORT_CHRONOLOGICAL_SUCCESS = "List has been sorted in chronological order.";

    private final String option;

    public SortCommand(String option) {
        this.option = option;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String message;

        if (option.equals(OPTION_ALPHABETICAL)) {
            model.sortPersonList(new NameComparator());
            message = MESSAGE_SORT_ALPHABETICAL_SUCCESS;
        } else { // order.equals(OPTION_CHRONOLOGICAL)
            model.sortPersonList(new DateComparator());
            message = MESSAGE_SORT_CHRONOLOGICAL_SUCCESS;
        }

        return new CommandResult(message);
    }

    class NameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.compareTo(p2);
        }
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
