package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_TIME;
import static seedu.booking.commons.core.Messages.MESSAGE_OVERLAPPING_BOOKING;
import static seedu.booking.commons.core.Messages.PROMPT_NEWDATE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_START_MESSAGE;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_START;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
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

        StatefulLogicManager.processStateInput(endTime);
        StatefulLogicManager.setState(STATE_START);
        CommandResult result;

        try {
            Booking booking = (Booking) StatefulLogicManager.create();
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

        StatefulLogicManager.setStateInactive();

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptBookingEndCommand // instanceof handles nulls
                && this.endTime.equals(((PromptBookingEndCommand) other).endTime));
    }
}
