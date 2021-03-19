package fooddiary.logic.commands;

import fooddiary.commons.core.Messages;
import fooddiary.model.Model;
import fooddiary.model.entry.NameContainsAllKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all food places whose entries contain all of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAllCommand extends Command {

    public static final String COMMAND_WORD = "findall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all food places whose entries contain all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 5/5 fastfood";

    private final NameContainsAllKeywordsPredicate predicate;

    public FindAllCommand(NameContainsAllKeywordsPredicate predicate) {
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
                || (other instanceof FindAllCommand // instanceof handles nulls
                && predicate.equals(((FindAllCommand) other).predicate)); // state check
    }
}
