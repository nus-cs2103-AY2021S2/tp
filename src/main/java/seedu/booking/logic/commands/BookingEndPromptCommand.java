package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.EndTime;

public class BookingEndPromptCommand extends Command {

    private final EndTime endTime;

    public BookingEndPromptCommand(EndTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(endTime);

        CommandResult result;

        Booking booking = (Booking) ModelManager.create();
        result = new AddBookingCommand(booking).execute(model);

        ModelManager.setStateInactive();

        return result;
    }
}
