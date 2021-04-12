package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_VENUES;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.venue.Venue;

/**
 * Adds a booking venue to the system.
 */

public class AddVenueCommand extends Command {
    public static final String MESSAGE_SUCCESS = "New venue added: %1$s";
    public static final String MESSAGE_DUPLICATE_VENUE = "This venue already exists in the system.";

    private final Venue toAdd;

    /**
     * Creates an AddVenue to add the specified {@code Venue}
     */

    public AddVenueCommand(Venue venue) {
        requireNonNull(venue);
        toAdd = venue;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasVenue(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_VENUE);
        }

        model.addVenue(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_SHOW_VENUES);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVenueCommand // instanceof handles nulls
                && toAdd.equals(((AddVenueCommand) other).toAdd));
    }
}
