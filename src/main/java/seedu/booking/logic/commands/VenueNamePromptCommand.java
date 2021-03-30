package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_VENUE_NAME;
import static seedu.booking.commons.core.Messages.MESSAGE_PROMPT_TRYAGAIN;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.venue.VenueName;

public class VenueNamePromptCommand extends Command {

    private final VenueName venueName;

    public VenueNamePromptCommand(VenueName venueName) {
        this.venueName = venueName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasVenueWithVenueName(venueName)) {
            throw new CommandException(MESSAGE_INVALID_VENUE_NAME + MESSAGE_PROMPT_TRYAGAIN);
        }

        ModelManager.processStateInput(venueName);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage());
    }
}
