package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.issue.IssueContainsKeywordsPredicate;

/**
 * Finds and lists all issues in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindIssueCommand extends Command {

    public static final String COMMAND_WORD = "ifind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all issues whose room number, description, or tags contain any of "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 10-100 12-120 Broken";

    private final IssueContainsKeywordsPredicate predicate;

    public FindIssueCommand(IssueContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIssueList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ISSUES_LISTED_OVERVIEW, model.getFilteredIssueList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIssueCommand // instanceof handles nulls
                        && predicate.equals(((FindIssueCommand) other).predicate)); // state check
    }

}
