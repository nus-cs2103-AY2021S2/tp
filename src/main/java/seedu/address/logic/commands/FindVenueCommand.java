package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Venue;
import seedu.address.model.booking.VenueNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists the venue in the system whose id corresponds to that of the argument keyword.
 * Keyword matching is case insensitive.
 */
public class FindVenueCommand extends Command {

    public static final String COMMAND_WORD = "find_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all venues whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: name...\n"
            + "Example: " + COMMAND_WORD + " v/Victoria Hall";

    private final VenueNameContainsKeywordsPredicate predicate;

    public FindVenueCommand(VenueNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVenueList(predicate);
        System.out.println(model.getFilteredVenueList());
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
