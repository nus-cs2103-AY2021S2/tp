package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entry.EntryNameContainsKeywordsPredicate;

/**
 * Finds and lists all entries in Teaching Assistant whose name contains all of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEntryCommand extends Command {

    public static final String COMMAND_WORD = "efind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all entries whose name contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " consultation";

    private final EntryNameContainsKeywordsPredicate predicate;

    /**
     * Creates a FindEntryCommand to find the relevant entries according to the specified {@code predicate}.
     */
    public FindEntryCommand(EntryNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW, model.getFilteredEntryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEntryCommand // instanceof handles nulls
                && predicate.equals(((FindEntryCommand) other).predicate)); // state check
    }
}
