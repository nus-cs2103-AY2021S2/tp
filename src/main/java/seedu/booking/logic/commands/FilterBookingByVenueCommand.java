package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.BookingContainsVenuePredicate;

/**
 * Finds and lists bookings in the system whose venue corresponds to the venue name given as argument
 * Venue name matching is case insensitive.
 */
public class FilterBookingByVenueCommand extends Command {

    public static final String COMMAND_WORD = "filter_booking_by_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings under the venue "
            + "the specified venue name (case-insensitive) and displays them as a list.\n"
            + "Parameters: v/venueName\n"
            + "Example: " + COMMAND_WORD + " v/Victoria Hall";

    private final BookingContainsVenuePredicate predicate;

    public FilterBookingByVenueCommand(BookingContainsVenuePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(predicate);
        if (model.getFilteredBookingList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_BOOKING_FILTER_FAILED);
        } else {
            return new CommandResult(
                    Messages.MESSAGE_BOOKING_VENUE_FILTERED + predicate.getVenueName());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBookingByVenueCommand // instanceof handles nulls
                && predicate.equals(((FilterBookingByVenueCommand) other).predicate)); // state check
    }
}
