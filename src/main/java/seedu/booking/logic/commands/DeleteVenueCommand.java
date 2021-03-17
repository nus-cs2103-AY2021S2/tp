package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.venue.Venue;

/**
 * Deletes an existing venue from the system.
 */
public class DeleteVenueCommand extends Command {

    public static final String COMMAND_WORD = "delete_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the venue identified by the venue name used in the displayed list.\n"
            + "Parameters: v/VENUE NAME\n"
            + "Example: " + COMMAND_WORD + " v/Sports Hall";

    public static final String MESSAGE_DELETE_VENUE_SUCCESS = "Deleted Venue: %1$s";

    private final Venue targetVenue;

    public DeleteVenueCommand(Venue targetVenue) {
        this.targetVenue = targetVenue;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Venue> lastShownList = model.getFilteredVenueList();

        if (!lastShownList.stream().anyMatch(targetVenue::isSameVenue)) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENUE_NAME);
        }

        model.deleteVenue(targetVenue);
        return new CommandResult(String.format(MESSAGE_DELETE_VENUE_SUCCESS, targetVenue.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteVenueCommand // instanceof handles nulls
                && targetVenue.equals(((DeleteVenueCommand) other).targetVenue)); // state check
    }
}
