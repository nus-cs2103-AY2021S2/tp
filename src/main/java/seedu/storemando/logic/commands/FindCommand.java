package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;

/**
 * Finds and lists all items in storemando whose item name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final ItemNameContainsKeywordsPredicate predicate;

    public FindCommand(ItemNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(predicate);
        int numberOfItems = model.getFilteredItemList().size();
        if (numberOfItems > 1) {
            return new CommandResult(
                String.format(Messages.MESSAGE_MORE_THAN_ONE_ITEM_LISTED_OVERVIEW, numberOfItems));
        } else {
            return new CommandResult(
                String.format(Messages.MESSAGE_LESS_THAN_TWO_ITEMS_LISTED_OVERVIEW, numberOfItems));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
