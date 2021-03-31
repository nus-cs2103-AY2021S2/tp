package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.BookingWithinDatePredicate;

public class FilterBookingByDateCommand extends Command {

    public static final String COMMAND_WORD = "filter_booking_by_date";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings on the date given by "
            + "the specified date and displays them as a list.\n"
            + "Parameters: date/date\n"
            + "Example: " + COMMAND_WORD + " date/2020-02-12";

    private final BookingWithinDatePredicate predicate;

    public FilterBookingByDateCommand(BookingWithinDatePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(predicate);
        if (model.getFilteredBookingList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_BOOKING_FILTER_FAILED + " on " + predicate.getDateString());
        } else {
            return new CommandResult(
                    Messages.MESSAGE_BOOKING_DATE_FILTERED + predicate.getDateString());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBookingByDateCommand // instanceof handles nulls
                && predicate.equals(((FilterBookingByDateCommand) other).predicate)); // state check
    }
}
