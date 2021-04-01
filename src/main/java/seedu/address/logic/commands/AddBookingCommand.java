package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.Residence;


/**
 * Adds a {@code Booking} to a {@code Residence} tracker.
 */
//@@author Soorya
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "addb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to a specific residence.\n"
            + "Parameters: RESIDENCE_INDEX (must be a positive integer)"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_BOOKING_START_DATE + "START_DATE "
            + PREFIX_BOOKING_END_DATE + "END_DATE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Sandy "
            + PREFIX_PHONE + "87654321 "
            + PREFIX_BOOKING_START_DATE + "09-08-2021 "
            + PREFIX_BOOKING_END_DATE + "11-08-2021 ";
    public static final String MESSAGE_SUCCESS = "New booking added to Residence %1$s : %2$s";
    public static final String MESSAGE_INVALID_BOOKING = "The specified date overlaps "
            + "with another booking for this residence";

    private final Index targetIndex;
    private final Booking toAdd;

    /**
     * Creates an AddBookingCommand to add the specified {@code Residence}
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
        if (residenceToAddBooking.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_INVALID_BOOKING);
        }

        model.setResidence(residenceToAddBooking, residenceToAddBooking.addBooking(toAdd));
        model.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                residenceToAddBooking.getResidenceName().toString(), toAdd));
    }

    public static String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookingCommand // instanceof handles nulls
                && toAdd.equals(((AddBookingCommand) other).toAdd));
    }

}
