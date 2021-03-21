package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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

    private final String order;

    public static final String MESSAGE_SORT_ALPHABETICAL_SUCCESS = "List has been sorted in alphabetical order.";
    public static final String MESSAGE_SORT_CHRONOLOGICAL_SUCCESS = "List has been sorted in chronological order.";
    public static final String MESSAGE_SORT_FAILURE = "Sorting FAILED."
            + "Please double check usage as follows.\n" + MESSAGE_USAGE;

    public static final String MESSAGE_SORT_CHRONOLOGICAL_IN_PROGRESS = "Chronological sort is still being implemented.";

    public SortCommand(String order) {
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Person> lastShownList = model.getFilteredPersonList();
        String message;

        if (order.equals(ORDER_ALPHABETICAL)) {
            SortedList<Person> sortedPersons = lastShownList.sorted();
            model.setPersons(sortedPersons);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            message = MESSAGE_SORT_ALPHABETICAL_SUCCESS;
        } else if (order.equals(ORDER_CHRONOLOGICAL)) {
            message = MESSAGE_SORT_CHRONOLOGICAL_IN_PROGRESS;
        } else {
            message = MESSAGE_SORT_FAILURE;
        }
        return new CommandResult(message);
    }
}
