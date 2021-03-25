package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Booking;

/**
 * Displays all existing bookings to the terminal
 */
public class ListBookingCommand extends Command {

    public static final String COMMAND_WORD = "list_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all existing bookings.\n"
            + "Parameters: NILL\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_BOOKING_LISTED_SUCCESS = "Here are all current bookings:\n";

    public static final String MESSAGE_BOOKING_LISTED_LINEBREAK = "-------------------------------\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();
        String outputString = "";

        if (model.getFilteredBookingList().size() == 0) {
            throw new CommandException(Messages.MESSAGE_BOOKING_LISTED_EMPTY);
        }

        outputString += MESSAGE_BOOKING_LISTED_SUCCESS;

        for (Booking booking : lastShownList) {
            outputString += MESSAGE_BOOKING_LISTED_LINEBREAK;
            outputString += ("Booking ID: " + String.valueOf(booking.getId()) + "\n");
            outputString += ("Venue Name: " + booking.getVenueName() + "\n");
            outputString += ("Booker Email: " + booking.getBookerEmail() + "\n");
            outputString += ("Description: " + booking.getDescription() + "\n");
            outputString += ("From: " + booking.getBookingStart() + "\n");
            outputString += ("Till: " + booking.getBookingEnd() + "\n");
            outputString += (MESSAGE_BOOKING_LISTED_LINEBREAK);
        }

        return new CommandResult(outputString);
    }
}
