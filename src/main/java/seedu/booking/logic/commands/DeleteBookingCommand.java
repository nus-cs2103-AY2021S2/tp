package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.core.index.Index;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Booking;

/**
 * Changes the remark of an existing person in the booking system.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "delete_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by its booking id.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted booking: %1$s";

    private final Index targetIndex;

    public DeleteBookingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_BOOKING_INDEX_OUT_OF_RANGE);
        }
        Booking bookingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBooking(bookingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookingCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBookingCommand) other).targetIndex)); // state check
    }
}

