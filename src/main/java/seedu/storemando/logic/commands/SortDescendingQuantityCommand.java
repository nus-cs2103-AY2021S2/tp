package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.comparator.ItemComparatorByDecreasingQuantity;

public class SortDescendingQuantityCommand extends SortCommand {
    public static final String MESSAGE_SUCCESS_QUANTITY_DESC = "Sorted all items in descending order of quantity.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> currentList = model.getFilteredItemList();
        if (currentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_ITEM_IN_LIST);
        }

        Comparator<Item> comparator = new ItemComparatorByDecreasingQuantity();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS_QUANTITY_DESC);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortDescendingQuantityCommand);
    }
}
