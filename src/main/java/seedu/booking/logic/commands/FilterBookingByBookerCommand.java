package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.BookingContainsBookerPredicate;

/**
 * Finds and lists bookings in the system whose booker name corresponds to the booker name given as argument
 * Booker name is case sensitive
 */
public class FilterBookingByBookerCommand extends Command {

    public static final String COMMAND_WORD = "filter_booking_by_booker";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings booked by the booker "
            + "the specified booker name (case-sensitive) and displays them as a list.\n"
            + "Parameters: n/bookerName\n"
            + "Example: " + COMMAND_WORD + " n/John Rose";

    private final BookingContainsBookerPredicate predicate;

    public FilterBookingByBookerCommand(BookingContainsBookerPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(predicate);
        if (model.getFilteredBookingList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_BOOKING_FILTER_FAILED + " by " + predicate.getBookerEmail());
        } else {
            return new CommandResult(
                    Messages.MESSAGE_BOOKING_PERSON_FILTERED + predicate.getBookerEmail());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBookingByBookerCommand // instanceof handles nulls
                && predicate.equals(((FilterBookingByBookerCommand) other).predicate)); // state check
    }
}
