package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.contact.TagsContainsKeywordsPredicate;

/**
 * Finds and lists all contacts in the contacts list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of, "
            + "and tags contain all of, the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS]... "
            + "[" + PREFIX_TAG + "TAG_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alice "
            + PREFIX_NAME + "bob "
            + PREFIX_TAG + "tutors ";

    private final NameContainsKeywordsPredicate namePredicate;
    private final TagsContainsKeywordsPredicate tagsPredicate;

    public FindContactCommand(NameContainsKeywordsPredicate namePredicate,
                              TagsContainsKeywordsPredicate tagsPredicate) {
        this.namePredicate = namePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // satisfy both name- and tag-matching.
        model.updateFilteredContactList(contact -> namePredicate.test(contact) && tagsPredicate.test(contact));
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && namePredicate.equals(((FindContactCommand) other).namePredicate)
                && tagsPredicate.equals(((FindContactCommand) other).tagsPredicate)); // state check
    }
}
