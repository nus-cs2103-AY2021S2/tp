package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_VENUES;

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

    public static final String MESSAGE_SUCCESS = "Listed all venues";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredVenueList(PREDICATE_SHOW_ALL_VENUES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
