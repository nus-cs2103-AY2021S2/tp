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

        try {
            Booking booking = (Booking) ModelManager.create();
            result = new AddBookingCommand(booking).execute(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            ModelManager.setStateInactive();
            throw new CommandException("Failed to create booking, please try again.\n");
        }

        ModelManager.setStateInactive();

        return result;
    }
}
