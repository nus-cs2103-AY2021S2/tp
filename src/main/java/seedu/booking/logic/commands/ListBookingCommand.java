package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;

/**
 * Displays all existing bookings to the terminal
 */
public class ListBookingCommand extends Command {

    public static final String COMMAND_WORD = "list_booking";

    public static final String MESSAGE_BOOKING_LISTED_SUCCESS = "Here are all bookings currently in the system:\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(MESSAGE_BOOKING_LISTED_SUCCESS, COMMAND_SHOW_BOOKINGS);
    }
}
