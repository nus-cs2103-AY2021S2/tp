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
            + "Parameters: NIL\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VENUE_LISTED_SUCCESS = "Here are all venues currently in the system:\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Venue> lastShownList = model.getFilteredVenueList();
        final StringBuilder outputString = new StringBuilder();

        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_VENUE_LISTED_EMPTY);
        }

        outputString.append(MESSAGE_VENUE_LISTED_SUCCESS);
        return new CommandResult(outputString.toString());
    }
}
