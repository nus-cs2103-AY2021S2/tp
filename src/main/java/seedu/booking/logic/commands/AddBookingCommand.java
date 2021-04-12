package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Booking;

/**
 * Adds a booking to the booking system.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "add_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the booking system. "
            + "Parameters: "
            + PREFIX_EMAIL + "BOOKER EMAIL "
            + PREFIX_VENUE + "VENUE NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_BOOKING_START + "DATETIME "
            + PREFIX_BOOKING_END + "DATETIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "example@gmail.com "
            + PREFIX_VENUE + "Hall "
            + PREFIX_DESCRIPTION + "For FYP Meeting. "
            + PREFIX_BOOKING_START + "2012-01-31 22:59 "
            + PREFIX_BOOKING_END + "2012-01-31 23:59";

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in the booking system.";
    public static final String MESSAGE_INVALID_TIME =
            "Invalid timing: The booking's starting time cannot be later than its ending time. ";
    public static final String MESSAGE_INVALID_VENUE = "This venue does not exist in the system.";
    public static final String MESSAGE_INVALID_PERSON = "This booker does not exist in the system.";
    public static final String MESSAGE_OVERLAPPING_BOOKING = "This time slot has been booked.";
    private final Booking toAdd;

    /**
     * Creates an CreateBookingCommand to add the specified {@code Booking}
     */
    public AddBookingCommand(Booking booking) {
        requireNonNull(booking);
        toAdd = booking;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        if (!toAdd.isValidTime()) {
            throw new CommandException(MESSAGE_INVALID_TIME);
        }

        if (!model.hasPersonWithEmail(toAdd.getBookerEmail())) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        if (!model.hasVenueWithVenueName(toAdd.getVenueName())) {
            throw new CommandException(MESSAGE_INVALID_VENUE);
        }

        if (model.hasOverlappedBooking(toAdd)) {
            throw new CommandException(MESSAGE_OVERLAPPING_BOOKING);
        }

        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookingCommand // instanceof handles nulls
                && toAdd.equals(((AddBookingCommand) other).toAdd));
    }
}
