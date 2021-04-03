package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.*;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_START;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.EndTime;

public class PromptBookingEndCommand extends Command {

    private final EndTime endTime;

    public PromptBookingEndCommand(EndTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(endTime);
        ModelManager.setState(STATE_START);
        CommandResult result;

        try {
            Booking booking = (Booking) ModelManager.create();
            if (!booking.isValidTime()) {
                throw new CommandException(MESSAGE_INVALID_TIME);
            }
            if (model.hasOverlappedBooking(booking)) {
                throw new CommandException(MESSAGE_OVERLAPPING_BOOKING);
            }

            result = new AddBookingCommand(booking).execute(model);

        } catch (CommandException ex) {
            throw new CommandException(ex.getMessage() + PROMPT_NEWDATE_MESSAGE + PROMPT_START_MESSAGE);
        }

        ModelManager.setStateInactive();

        return result;
    }
}
