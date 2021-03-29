package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Alternatively, specify fields to search using flags. Only one field is allowed at a time.\n"
            + "Parameters: [-t TAG_KEYWORD [MORE_TAG_KEYWORDS]]\n"
            + "Parameters: [-n NAME_KEYWORD [MORE_NAME_KEYWORDS]]\n"
            + "Parameters: [-e EMAIL_KEYWORD [MORE_EMAIL_KEYWORDS]]\n"
            + "Parameters: [-r REMARK_KEYWORD [MORE_REMARK_KEYWORDS]]\n"
            + "Example: " + COMMAND_WORD + " -t friend colleague classmate";

    private final Predicate<Person> predicate;
    private final Comparator<Person> comparator;


    public FindCommand(Predicate<Person> predicate, Comparator<Person> comparator) {
        this.predicate = predicate;
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.updateSortedFilteredPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && (predicate.equals(((FindCommand) other).predicate)
                && comparator.equals(((FindCommand) other).comparator))); // state check
    }
}
