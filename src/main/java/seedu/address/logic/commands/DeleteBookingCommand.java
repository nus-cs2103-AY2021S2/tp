/*
package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.Residence;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "deleteb";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by the booking index of the "
            + "residence identified by residence index used in the displayed residence list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 1";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Residence %1$d Booking %1$s";

    private final Index residenceIndex;
    private final Index bookingIndex;

    public DeleteBookingCommand(Index residenceIndex, Index bookingIndex) {
        this.residenceIndex = residenceIndex;
        this.bookingIndex = bookingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Residence> lastShownList = model.getFilteredResidenceList();
        Residence targetResidence = lastShownList.get(residenceIndex.getZeroBased());
        List<Booking> bookingList = targetResidence.getBookingDetails();
        if (residenceIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
        }

        if (bookingIndex.getZeroBased() >= bookingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToDelete = bookingList.get(bookingIndex.getZeroBased());

        model.deleteBooking(bookingToDelete);
        //model.deleteResidence(residenceToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete));
    }

}
*/
