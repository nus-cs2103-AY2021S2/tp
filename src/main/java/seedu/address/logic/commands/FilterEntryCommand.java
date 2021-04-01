package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entry.EntryTagsContainKeywordsPredicate;

public class FilterEntryCommand extends Command {
    public static final String COMMAND_WORD = "efilter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all entries that have the tags of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " student english";

    private final EntryTagsContainKeywordsPredicate predicate;

    public FilterEntryCommand(EntryTagsContainKeywordsPredicate predicate) {
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
                || (other instanceof FilterEntryCommand // instanceof handles nulls
                && predicate.equals(((FilterEntryCommand) other).predicate)); // state check
    }
}
