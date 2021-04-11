package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_CAPACITY_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_DUPLICATE_VENUE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.states.AddVenueCommandState.STATE_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddVenueCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.Model;
import seedu.booking.model.venue.VenueName;

/**
 * Sets up necessary conditions for multi-step command for create booking
 */
public class PromptAddVenueCommand extends Command {
    public static final String COMMAND_WORD = "add_venue";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_VENUE + "VENUE_NAME: Starts multi-step process to add venue.\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENUE + "Victoria Hall";

    private final VenueName venueName;

    public PromptAddVenueCommand(VenueName venueName) {
        this.venueName = venueName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasVenueWithVenueName(venueName)) {
            throw new CommandException(PROMPT_DUPLICATE_VENUE_MESSAGE);
        }

        CommandState commandState = new AddVenueCommandState(venueName);
        StatefulLogicManager.setCommandState(commandState);
        StatefulLogicManager.setStateActive();
        StatefulLogicManager.setState(STATE_CAPACITY);
        return new CommandResult(PROMPT_CAPACITY_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptAddVenueCommand // instanceof handles nulls
                && venueName.equals(((PromptAddVenueCommand) other).venueName));
    }
}
