package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all students in TutorsPet by lesson day and time.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort students by lesson day and time\n "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all students by lesson day and time";

    private final Comparator<Person> comparator;
    private final Predicate<Person> predicate;

    /**
     * Creates a SortCommand object that has a specific {@code Predicate} and {@code comparator}.
     */
    public SortCommand(Predicate<Person> predicate, Comparator<Person> comparator) {
        this.comparator = comparator;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // model.updateSortedPersonList(comparator);
        model.filterThenSortPersonList(predicate, comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getTransformedPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }

}
