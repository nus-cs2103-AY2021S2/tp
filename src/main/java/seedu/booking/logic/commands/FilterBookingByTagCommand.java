package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.BookingContainsTagPredicate;

/**
 * Finds and lists bookings in the system whose venue corresponds to the venue name given as argument
 * Venue name matching is case insensitive.
 */
public class FilterBookingByTagCommand extends Command {

    public static final String COMMAND_WORD = "filter_booking_by_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings with the tag "
            + "the specified tag name (case-insensitive) and displays them as a list.\n"
            + "Parameters: t/tag\n"
            + "Example: " + COMMAND_WORD + " t/Central";

    private final BookingContainsTagPredicate predicate;

    public FilterBookingByTagCommand(BookingContainsTagPredicate predicate) {
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
                    Messages.MESSAGE_BOOKING_TAG_FILTERED + predicate.getTagName());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBookingByTagCommand // instanceof handles nulls
                && predicate.equals(((FilterBookingByTagCommand) other).predicate)); // state check
    }
}
