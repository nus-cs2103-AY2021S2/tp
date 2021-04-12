package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.predicate.ItemNameContainsKeywordsPredicate;
import seedu.storemando.model.item.predicate.ItemNameContainsPartialKeywordsPredicate;

/**
 * Finds and lists all items in storemando whose item name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and displays all items whose names contain "
        + "any of the specified keywords (case-insensitive).\n"
        + "Parameters: [*/]KEYWORD [MORE_KEYWORDS]...\n"
        + "Examples:\n"
        + "1. " + COMMAND_WORD + " potato chip \n"
        + "2. " + COMMAND_WORD + " */cheese egg";

    public static final String MESSAGE_LESS_THAN_TWO_ITEMS_FOUND_OVERVIEW = "%1$d item found!";
    public static final String MESSAGE_MORE_THAN_ONE_ITEM_FOUND_OVERVIEW = "%1$d items found!";

    /**
     * The predicate that find command uses to filter the items.
     */
    private final Predicate<Item> predicate;

    public FindCommand(ItemNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(ItemNameContainsPartialKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> currentList = model.getFilteredItemList();
        if (currentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_ITEM_IN_LIST);
        }
        model.updateCurrentPredicate(predicate);
        model.updateFilteredItemList(model.getCurrentPredicate());
        int numberOfItems = model.getFilteredItemList().size();
        if (numberOfItems > 1) {
            return new CommandResult(
                String.format(MESSAGE_MORE_THAN_ONE_ITEM_FOUND_OVERVIEW, numberOfItems));
        } else {
            return new CommandResult(
                String.format(MESSAGE_LESS_THAN_TWO_ITEMS_FOUND_OVERVIEW, numberOfItems));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
