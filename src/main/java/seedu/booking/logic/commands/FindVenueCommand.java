package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_VENUES;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.venue.Venue;

/**
 * Finds and lists the venues in the system that matches the specified fields.
 */
public class FindVenueCommand extends Command {

    public static final String COMMAND_WORD = "find_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all venues that match the specified fields.\n"
            + "At least one field must be provided.\n"
            + "Parameters: " + COMMAND_WORD + " "
            + "[" + PREFIX_VENUE + "VENUE_NAME] "
            + "[" + PREFIX_CAPACITY + "CAPACITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENUE + "Victorias Hall "
            + PREFIX_CAPACITY + "10 "
            + PREFIX_DESCRIPTION + "Cool concert place "
            + PREFIX_TAG + "Central";

    private final List<Predicate<Venue>> predicateList;

    public FindVenueCommand(List<Predicate<Venue>> predicates) {
        this.predicateList = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Venue> predicate = combineVenuePredicates(predicateList);
        model.updateFilteredVenueList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_VENUE_DISPLAYED, model.getFilteredVenueList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindVenueCommand // instanceof handles nulls
                && predicateList.equals(((FindVenueCommand) other).predicateList)); // state check
    }

    /**
     * Returns a composition of the predicates in the given venue predicate list.
     */
    private static Predicate<Venue> combineVenuePredicates(List<Predicate<Venue>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_VENUES);
    }
}
