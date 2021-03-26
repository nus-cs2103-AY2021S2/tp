package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.Residence;


/**
 * Adds a residence to the residence tracker.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "addb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to a specific residence.\n"
            + "Parameters: RESIDENCE_INDEX (must be a positive integer)"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_BOOKING_START_TIME + "START_TIME "
            + PREFIX_BOOKING_END_TIME + "END_TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Sandy "
            + PREFIX_PHONE + "87654321 "
            + PREFIX_BOOKING_START_TIME + "09082021 "
            + PREFIX_BOOKING_END_TIME + "11082021 ";
    public static final String MESSAGE_SUCCESS = String.format("New booking added to Residence %1$s : %2$s");
    public static final String MESSAGE_INVALID_BOOKING = "The specified time overlaps "
            + "with another booking for this residence";

    private final Index targetIndex;
    private final Booking toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Residence}
     */
    public AddBookingCommand(Index targetIndex, Booking booking) {
        requireNonNull(booking);
        this.targetIndex = targetIndex;
        this.toAdd = booking;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Residence> lastShownList = model.getFilteredResidenceList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
        }

        Residence residenceToAddBooking = lastShownList.get(targetIndex.getZeroBased());
        model.setResidence(residenceToAddBooking, residenceToAddBooking.addBooking(toAdd));
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                residenceToAddBooking.getResidenceName().getValue(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookingCommand // instanceof handles nulls
                && toAdd.equals(((AddBookingCommand) other).toAdd));
    }

}
