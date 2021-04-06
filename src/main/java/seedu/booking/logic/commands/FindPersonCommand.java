package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.person.EmailContainsKeywordsPredicate;

/**
 * Finds and lists the person in the system whose email corresponds to that of the argument keyword.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "find_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose email matches "
            + "the specified keyword (case-insensitive) and displays them as a list.\n"
            + "Parameters: e/EMAIL\n"
            + "Example: " + COMMAND_WORD + " e/johndoe@gmail.com";

    private final EmailContainsKeywordsPredicate predicate;

    public FindPersonCommand(EmailContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
