package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.venue.Venue;

/**
 * Adds a booking venue to the system.
 */

public class AddVenueCommand extends Command {

    /*
    public static final String COMMAND_WORD = "add_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking venue to the system. "
            + "Parameters: "
            + PREFIX_VENUE + "NAME "
            + "[" + PREFIX_CAPACITY + "MAXIMUM CAPACITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENUE + "Victoria Hall "
            + PREFIX_CAPACITY + "50 "
            + PREFIX_DESCRIPTION + "Classic concert hall "
            + PREFIX_TAG + "Central";

     */
    
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVenueCommand // instanceof handles nulls
                && toAdd.equals(((AddVenueCommand) other).toAdd));
    }
}
