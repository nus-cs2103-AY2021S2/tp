package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Changes the remark of an existing person in the address book.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "delete_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": delete the booking indicated  "
            + "by the the booking ID indicated\n. "
            + "Parameters: Booking ID (must be a positive integer) "
            + "delete_booking [ID]\n"
            + "Example: " + COMMAND_WORD + " 12345 ";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted booking: %1$d";

    private final int bookingId;

    public DeleteBookingCommand(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.deleteBooking(bookingId);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, bookingId));
    }
}