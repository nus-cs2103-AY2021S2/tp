package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.ReturnTruePredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names and/or"
            + "tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME_KEYWORD [MORE_NAME_KEYWORDS]... "
            + PREFIX_TAG + "TAG_KEYWORD [MORE_TAG_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alice bob charlie "
            + PREFIX_TAG + "friends neighbours";

    private final Predicate<Person> namePredicate;
    private final Predicate<Person> tagPredicate;

    /**
     * Creates a FindCommand to find the {@code Person}s with matching keywords.
     * @param namePredicate Predicate made up of names to match.
     * @param tagPredicate Predicate made up of tags to match.
     */
    public FindCommand(Predicate<Person> namePredicate,
                       Predicate<Person> tagPredicate) {
        assert (namePredicate instanceof NameContainsKeywordsPredicate
                || namePredicate instanceof ReturnTruePredicate);
        assert (tagPredicate instanceof PersonTagContainsKeywordsPredicate
                || tagPredicate instanceof ReturnTruePredicate);

        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.and(tagPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && tagPredicate.equals(((FindCommand) other).tagPredicate)); // state check
    }
}
