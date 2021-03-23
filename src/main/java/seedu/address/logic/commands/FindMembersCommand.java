package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DetailsContainsKeywordsPredicate;

/**
 * Finds and lists all persons in HEY MATEz whose details contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMembersCommand extends Command {

    public static final String COMMAND_WORD = "findMembers";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all members whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice 97574218 rachel@example.com";

    private final DetailsContainsKeywordsPredicate predicate;

    public FindMembersCommand(DetailsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMembersCommand // instanceof handles nulls
                && predicate.equals(((FindMembersCommand) other).predicate)); // state check
    }
}
