package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.contact.EmailContainsKeywordsPredicate;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.contact.TagsContainKeywordsPredicate;

/**
 * Finds and lists all contacts in the contacts list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names and emails contain "
            + "any of, and tags contain all of, the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS]... "
            + "[" + PREFIX_EMAIL + "EMAIL_KEYWORDS]... "
            + "[" + PREFIX_TAG + "TAG_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alice "
            + PREFIX_NAME + "bob "
            + PREFIX_EMAIL + "example.com "
            + PREFIX_TAG + "tutors ";

    private final NameContainsKeywordsPredicate namePredicate;
    private final EmailContainsKeywordsPredicate emailPredicate;
    private final TagsContainKeywordsPredicate tagsPredicate;

    /**
     * Creates a new {@code FindContactCommand} with two predicates (i.e., conditions):
     * one applies to the contacts' names, and the other applies to their tags.
     * @param namePredicate The predicate to be evaluated against the contacts' names.
     * @param tagsPredicate The predicate to be evaluated against the contacts' tags.
     */
    public FindContactCommand(NameContainsKeywordsPredicate namePredicate,
                              EmailContainsKeywordsPredicate emailPredicate,
                              TagsContainKeywordsPredicate tagsPredicate) {
        this.namePredicate = namePredicate;
        this.emailPredicate = emailPredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // satisfy both name- and tag-matching.
        model.updateFilteredContactList(namePredicate.and(emailPredicate).and(tagsPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()),
                UiAction.OPEN, UiActionOption.CONTACT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && namePredicate.equals(((FindContactCommand) other).namePredicate)
                && emailPredicate.equals(((FindContactCommand) other).emailPredicate)
                && tagsPredicate.equals(((FindContactCommand) other).tagsPredicate)); // state check
    }
}
