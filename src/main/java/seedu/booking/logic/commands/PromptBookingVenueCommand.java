package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_NON_EXISTENT_VENUE_NAME;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.venue.VenueName;

public class PromptBookingVenueCommand extends Command {

    private final VenueName venueName;

    public PromptBookingVenueCommand(VenueName venueName) {
        this.venueName = venueName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasVenueWithVenueName(venueName)) {
            throw new CommandException(MESSAGE_NON_EXISTENT_VENUE_NAME + PROMPT_MESSAGE_TRY_AGAIN);
        }

        StatefulLogicManager.processStateInput(venueName);
        StatefulLogicManager.setNextState();
        return new CommandResult(StatefulLogicManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_BOOKINGS);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptBookingVenueCommand // instanceof handles nulls
                && venueName.equals(((PromptBookingVenueCommand) other).venueName));
    }
}
