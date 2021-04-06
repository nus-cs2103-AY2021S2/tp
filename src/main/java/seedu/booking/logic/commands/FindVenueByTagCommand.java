package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.VenueContainsTagPredicate;

/**
 * Finds and lists venues in the system whose venue corresponds to the tag name given as argument
 * Tag name matching is case insensitive.
 */
public class FindVenueByTagCommand extends Command {

    public static final String COMMAND_WORD = "find_venue_by_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all venues with the tag "
            + "the specified tag name (case-insensitive) and displays them as a list.\n"
            + "Parameters: t/tag\n"
            + "Example: " + COMMAND_WORD + " t/Central";

    private final VenueContainsTagPredicate predicate;

    public FindVenueByTagCommand(VenueContainsTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVenueList(predicate);
        if (model.getFilteredVenueList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_BOOKING_FILTER_FAILED);
        } else {
            return new CommandResult(
                    Messages.MESSAGE_BOOKING_TAG_FILTERED + predicate.getTagName());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindVenueByTagCommand // instanceof handles nulls
                && predicate.equals(((FindVenueByTagCommand) other).predicate)); // state check
    }
}
