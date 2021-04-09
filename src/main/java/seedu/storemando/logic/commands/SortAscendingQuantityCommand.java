package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemComparatorByIncreasingQuantity;

public class SortAscendingQuantityCommand extends SortCommand {
    public static final String MESSAGE_SUCCESS_QUANTITY_ASC = "Sorted all items based on their quantity in an"
        + " ascending order.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();
        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_NO_ITEMS_TO_SORT);
        }
        Comparator<Item> comparator = new ItemComparatorByIncreasingQuantity();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS_QUANTITY_ASC);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortAscendingQuantityCommand);
    }
}

