package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.booking.BookingIdContainsKeywordsPredicate;

/**
 * Finds and lists the bookings in the system whose id corresponds to that of the argument keyword.
 */
public class FindBookingCommand extends Command {

    public static final String COMMAND_WORD = "find_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings whose id contains "
            + "the specified keyword and displays them as a list with index numbers.\n"
            + "Parameters: id...\n"
            + "Example: " + COMMAND_WORD + " b/1";

    private final BookingIdContainsKeywordsPredicate predicate;

    public FindBookingCommand(BookingIdContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKING_DISPLAYED, model.getFilteredBookingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookingCommand // instanceof handles nulls
                && predicate.equals(((FindBookingCommand) other).predicate)); // state check
    }
}

