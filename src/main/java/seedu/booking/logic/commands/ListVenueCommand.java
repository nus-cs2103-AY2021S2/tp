package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_VENUES;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_VENUES;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;

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
        model.updateFilteredVenueList(PREDICATE_SHOW_ALL_VENUES);

        if (model.getFilteredVenueList().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_VENUE_LISTED_EMPTY);
        }

        return new CommandResult(MESSAGE_VENUE_LISTED_SUCCESS, COMMAND_SHOW_VENUES);
    }
}
