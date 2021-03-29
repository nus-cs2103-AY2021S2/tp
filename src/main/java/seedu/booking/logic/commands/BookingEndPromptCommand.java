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

        ModelManager.commandState.processInput(endTime);

        CommandResult result;

        try {
            Booking booking = (Booking) ModelManager.commandState.create();
            result = new CreateBookingCommand(booking).execute(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            ModelManager.commandState.setInactive();
            throw new CommandException("Failed to create booking, please try again.\n");
        }

        ModelManager.commandState.setInactive();

        return result;
    }
}
