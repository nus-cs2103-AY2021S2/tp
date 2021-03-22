package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.VenueNameContainsKeywordsPredicate;

/**
 * Finds and lists the venue in the system whose name corresponds to that of the argument keyword.
 * Keyword matching is case insensitive.
 */
public class FindVenueCommand extends Command {

    public static final String COMMAND_WORD = "find_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all venues whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: v/NAME\n"
            + "Example: " + COMMAND_WORD + " v/Victoria Hall";

    private final VenueNameContainsKeywordsPredicate predicate;

    public FindVenueCommand(VenueNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVenueList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_VENUE_DISPLAYED, model.getFilteredVenueList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindVenueCommand // instanceof handles nulls
                && predicate.equals(((FindVenueCommand) other).predicate)); // state check
    }
}
