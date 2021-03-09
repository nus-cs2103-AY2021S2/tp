package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKINGEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKINGSTART;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Adds a person to the address book.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "create_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the address book. "
            + "Parameters: "
            + PREFIX_BOOKER + "BOOKER "
            + PREFIX_VENUE + "VENUE "
            + PREFIX_DESCRIPTION + "DATETIME"
            + PREFIX_BOOKINGSTART + "DATETIME"
            + PREFIX_BOOKINGEND + "DATETIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKER + "John Doe "
            + PREFIX_VENUE + "UTOWN Hall 2 "
            + PREFIX_DESCRIPTION + "NA "
            + PREFIX_BOOKINGSTART + "2012-01-31 22:59:59 "
            + PREFIX_BOOKINGEND + "2012-01-31 23:59:59";

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This booking already exists in the address book";

    private final Booking toAdd;

    /**
     * Creates an AddBookingCommand to add the specified {@code Booking}
     */
    public AddBookingCommand(Booking booking) {
        requireNonNull(booking);
        toAdd = booking;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
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
