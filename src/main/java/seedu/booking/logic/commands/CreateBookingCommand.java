package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKER;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Booking;

/**
 * Adds a person to the address book.
 */
public class CreateBookingCommand extends Command {

    public static final String COMMAND_WORD = "create_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the address book. "
            + "Parameters: "
            + PREFIX_BOOKER + "BOOKER EMAIL "
            + PREFIX_VENUE + "VENUE NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_BOOKING_START + "DATETIME "
            + PREFIX_BOOKING_END + "DATETIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKER + "example@gmail.com "
            + PREFIX_VENUE + "Hall "
            + PREFIX_DESCRIPTION + "For FYP Meeting. "
            + PREFIX_BOOKING_START + "2012-01-31 22:59:59 "
            + PREFIX_BOOKING_END + "2012-01-31 23:59:59";

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in the address book.";
    public static final String MESSAGE_INVALID_TIME =
            "This booking's starting time is not earlier than the ending time.";
    public static final String MESSAGE_INVALID_VENUE = "This venue does not exist in the system.";
    public static final String MESSAGE_INVALID_PERSON = "This booker does not exist in the system.";
    private final Booking toAdd;

    /**
     * Creates an CreateBookingCommand to add the specified {@code Booking}
     */
    public CreateBookingCommand(Booking booking) {
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



        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateBookingCommand // instanceof handles nulls
                && toAdd.equals(((CreateBookingCommand) other).toAdd));
    }
}
