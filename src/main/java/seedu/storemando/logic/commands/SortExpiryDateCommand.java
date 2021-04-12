package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.comparator.ItemComparatorByExpiryDate;

/**
 * Sort the item in ascending order based on item's expiry date.
 */
public class SortExpiryDateCommand extends SortCommand {
    public static final String MESSAGE_SUCCESS_EXPIRYDATE_ASC = "Sorted all items based on their expiry date in"
        + " chronological order.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> currentList = model.getFilteredItemList();

        if (currentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_ITEM_IN_LIST);
        }

        ItemComparatorByExpiryDate comparator = new ItemComparatorByExpiryDate();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS_EXPIRYDATE_ASC);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortExpiryDateCommand);
    }
}
