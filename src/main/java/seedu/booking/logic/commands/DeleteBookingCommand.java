package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Id;

/**
 * Changes the remark of an existing person in the address book.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "delete_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by its booking id.\n"
            + "Parameters: Booking ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " bid/1234567890 ";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted booking: %1$d";

    private final Id bookingId;

    public DeleteBookingCommand(Id bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (!lastShownList.stream().anyMatch(booking -> booking.isId(bookingId))) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_ID);
        }

        model.deleteBooking(bookingId);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, bookingId));
    }
}

