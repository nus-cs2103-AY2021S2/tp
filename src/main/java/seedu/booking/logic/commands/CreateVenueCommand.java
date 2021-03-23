package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.venue.Venue;

/**
 * Adds a booking venue to the system.
 */
public class CreateVenueCommand extends Command {
    public static final String COMMAND_WORD = "create_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking venue to the system. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_CAPACITY + "MAXIMUM CAPACITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Victoria Hall "
            + PREFIX_CAPACITY + "50 "
            + PREFIX_DESCRIPTION + "Classic concert hall";

    public static final String MESSAGE_SUCCESS = "New venue added: %1$s";
    public static final String MESSAGE_DUPLICATE_VENUE = "This venue already exists in the system.";

    private final Venue toAdd;

    /**
     * Creates an AddVenue to add the specified {@code Venue}
     */
    public CreateVenueCommand(Venue venue) {
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
                || (other instanceof CreateVenueCommand // instanceof handles nulls
                && toAdd.equals(((CreateVenueCommand) other).toAdd));
    }
}
