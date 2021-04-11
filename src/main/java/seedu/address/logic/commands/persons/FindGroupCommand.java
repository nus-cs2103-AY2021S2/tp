package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose group contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindGroupCommand extends Command {

    public static final String COMMAND_WORD = "findpg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose groups contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " badminton tennis";

    private final GroupContainsKeywordsPredicate predicate;

    public FindGroupCommand(GroupContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindGroupCommand // instanceof handles nulls
                && predicate.equals(((FindGroupCommand) other).predicate)); // state check
    }
}
