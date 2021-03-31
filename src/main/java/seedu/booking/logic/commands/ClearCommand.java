package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;

/**
 * Clears the booking system.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "BookCoin has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBookingSystem(new BookingSystem());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
