package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Finds and lists all passengers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TripCommand extends Command {

    public static final String COMMAND_WORD = "trip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of trips"
            + "Parameters: PREFIX + KEYWORD\n"
            + "Example: \n1. " + COMMAND_WORD + " " + PREFIX_NAME + "alice"
            + "\n2. " + COMMAND_WORD + " " + PREFIX_TAG + "female"
            + "\n3. " + COMMAND_WORD + " " + PREFIX_PHONE + "91031282";

    private final Predicate<Pool> predicate;

    public TripCommand(Predicate<Pool> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(predicate);

        model.updateFilteredPoolList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PASSENGER_LISTED_OVERVIEW,
                        model.getFilteredPoolList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripCommand // instanceof handles nulls
                && predicate.equals(((TripCommand) other).predicate)); // state check
    }
}
