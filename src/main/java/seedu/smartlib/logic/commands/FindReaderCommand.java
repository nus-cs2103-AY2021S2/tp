package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;

/**
 * Finds and lists all readers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindReaderCommand extends Command {

    public static final String COMMAND_WORD = "findreader";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all readers whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindReaderCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredReaderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_READERS_LISTED_OVERVIEW, model.getFilteredReaderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindReaderCommand // instanceof handles nulls
                && predicate.equals(((FindReaderCommand) other).predicate)); // state check
    }
}
