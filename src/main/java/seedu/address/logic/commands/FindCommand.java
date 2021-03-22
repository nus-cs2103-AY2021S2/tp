package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.passenger.Passenger;

/**
 * Finds and lists all passengers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all passengers whose attributes"
            + ", defined by prefixes (case-sensitive),  contain any of the specified keywords (case-insensitive) and"
            + " displays them as a list with index numbers.\n"
            + "Do note that only 1 prefix and 1 argument can only be provided.\n"
            + "Parameters: PREFIX + KEYWORD\n"
            + "Example: \n1. " + COMMAND_WORD + " " + PREFIX_NAME + "alice"
            + "\n2. " + COMMAND_WORD + " " + PREFIX_TAG + "female"
            + "\n3. " + COMMAND_WORD + " " + PREFIX_PHONE + "91031282";

    private final Predicate<Passenger> predicate;

    public FindCommand(Predicate<Passenger> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPassengerList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PASSENGER_LISTED_OVERVIEW,
                        model.getFilteredPassengerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
