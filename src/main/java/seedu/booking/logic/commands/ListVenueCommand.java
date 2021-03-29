package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Venue;

/**
 * Displays all existing venues to the terminal
 */
public class ListVenueCommand extends Command {

    public static final String COMMAND_WORD = "list_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all existing venues.\n"
            + "Parameters: NIL\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VENUE_LISTED_SUCCESS = "Here are all venues currently in the system:\n";

    public static final String MESSAGE_VENUE_LISTED_LINEBREAK = "-------------------------------\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Venue> lastShownList = model.getFilteredVenueList();
        final StringBuilder outputString = new StringBuilder();

        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_VENUE_LISTED_EMPTY);
        }

        outputString.append(MESSAGE_VENUE_LISTED_SUCCESS);

        for (Venue venue : lastShownList) {
            outputString.append(MESSAGE_VENUE_LISTED_LINEBREAK);
            outputString.append("Venue Name: " + venue.getVenueName() + "\n");
            outputString.append("Capacity: " + venue.getCapacity() + "\n");
            outputString.append("Description: " + venue.getDescription() + "\n");

            Set<Tag> tags = venue.getTags();
            if (!tags.isEmpty()) {
                outputString.append("Tags: ");
                tags.forEach(outputString::append);
            }
            outputString.append("\n" + MESSAGE_VENUE_LISTED_LINEBREAK);
        }
        return new CommandResult(outputString.toString());
    }
}
