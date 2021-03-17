package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.venue.Venue;

/**
 * Displays all existing venues to the terminal
 */
public class ListVenueCommand extends Command {

    public static final String COMMAND_WORD = "list_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all existing venues.\n"
            + "Parameters: NILL\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VENUE_LISTED_SUCCESS = "Here are all current venues:\n";

    public static final String MESSAGE_VENUE_LISTED_LINEBREAK = "-------------------------------\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Venue> lastShownList = model.getFilteredVenueList();
        String outputString = "";

        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_VENUE_LISTED_EMPTY);
        }
        System.out.println(outputString);

        outputString += MESSAGE_VENUE_LISTED_SUCCESS;

        for (Venue venue : lastShownList) {
            outputString += MESSAGE_VENUE_LISTED_LINEBREAK;
            outputString += ("Venue Name: " + venue.getName() + "\n");
            outputString += ("Capacity: " + String.valueOf(venue.getCapacity()) + "\n");
            outputString += (MESSAGE_VENUE_LISTED_LINEBREAK);
        }

        System.out.println(outputString);

        return new CommandResult(outputString);

    }
}
