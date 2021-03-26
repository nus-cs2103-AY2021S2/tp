package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;

/**
 * Finds and lists all persons in PartyPlanet whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [-n NAME] [-t TAG]\n"
            + "Example: " + COMMAND_WORD + " -n Bob -t CS2030";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD + " [-n NAME] [-t TAG]";

    private final List<Predicate<Person>> predicates;

    public FindCommand(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> overallPredicate = x -> true;
        for (Predicate<Person> predicate: predicates) {
            overallPredicate = overallPredicate.and(predicate);
        }
        model.updateFilteredPersonList(overallPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
