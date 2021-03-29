package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.pool.Pool;

/**
 * Finds and lists all passengers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TripCommand extends Command {

    public static final String COMMAND_WORD = "trip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of trips where passenger's name contains "
            + "the keyword\n"
            + "Parameters: PREFIX + KEYWORD\n"
            + "Example: \n1. " + COMMAND_WORD + " " + PREFIX_NAME + "alice";

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
                String.format(Messages.MESSAGE_TRIPS_LISTED_OVERVIEW,
                        model.getFilteredPoolList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripCommand // instanceof handles nulls
                && predicate.equals(((TripCommand) other).predicate)); // state check
    }
}
