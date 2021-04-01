package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.sql.Timestamp;
import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Sorts the list of contacts in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of contacts in the address book.\n"
            + "Parameters: " + PREFIX_OPTION + "OPTION\n"
            + "Options: "
            + OPTION_NAME + " (for alphabetical order),\n"
            + OPTION_DATE + " (for chronological order)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_NAME + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_DATE;
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


        if (option.equals(OPTION_NAME)) {
            model.sortContactList(new NameComparator());
            message = MESSAGE_SORT_ALPHABETICAL_SUCCESS;
        } else { // order.equals(OPTION_DATE)
            model.sortContactList(new DateComparator());
            message = MESSAGE_SORT_CHRONOLOGICAL_SUCCESS;
        }

        return new CommandResult(message);
    }

    class NameComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact p1, Contact p2) {
            return p1.compareTo(p2);
        }
    }

    class DateComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact p1, Contact p2) {
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
