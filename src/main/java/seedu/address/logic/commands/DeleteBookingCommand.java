package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;


/**
 * Deletes a booking of residence identified using a booking index and residence index as displayed in ResidenceTracker.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "deleteb";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by the booking index of the "
            + "residence identified by residence index used in the displayed residence list.\n"
            + "Parameters: " + PREFIX_RESIDENCE + "RESIDENCE_INDEX (must be a positive integer) "
            + PREFIX_BOOKING + "BOOKING_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_RESIDENCE + "1 " + PREFIX_BOOKING + "1";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Residence %1$d's Booking: %2$s";

    private final Index residenceIndex;
    private final Index bookingIndex;

    /**
     * Constructs a {@code DeleteBookingCommand} with {@code residenceIndex} and {@code bookingIndex}
     */
    public DeleteBookingCommand(Index residenceIndex, Index bookingIndex) {
        this.residenceIndex = residenceIndex;
        this.bookingIndex = bookingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Residence> lastShownList = model.getFilteredResidenceList();
        if (residenceIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
        }
        Residence targetResidence = lastShownList.get(residenceIndex.getZeroBased());
        BookingList bookingList = targetResidence.getBookingList();

        if (bookingIndex.getZeroBased() >= bookingList.getBookingListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }
        Booking bookingToDelete = bookingList.getBooking(bookingIndex.getZeroBased());
        Residence residenceToDeleteBooking = lastShownList.get(residenceIndex.getZeroBased());
        residenceToDeleteBooking.getBookingList().remove(bookingToDelete);
        model.setResidence(targetResidence, residenceToDeleteBooking);
        model.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS,
                residenceIndex.getOneBased(), bookingToDelete));
    }

    public static String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookingCommand // instanceof handles nulls
                && residenceIndex.equals(((DeleteBookingCommand) other).residenceIndex)
                && bookingIndex.equals(((DeleteBookingCommand) other).bookingIndex)); // state check
    }
}

