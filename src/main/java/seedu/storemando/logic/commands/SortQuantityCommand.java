package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemComparatorByDecreasingQuantity;
import seedu.storemando.model.item.ItemComparatorByIncreasingQuantity;

public class SortQuantityCommand extends SortCommand {
    private boolean isAscending;

    /**
     * Creates an SortQuantityCommand to sort the items according to {@code isAscending}
     */
    public SortQuantityCommand(boolean isAscending) {
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();
        Comparator<Item> comparator;
        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_NO_ITEMS_TO_SORT);
        }

        if (this.isAscending) {
            comparator = new ItemComparatorByIncreasingQuantity();
        } else {
            comparator = new ItemComparatorByDecreasingQuantity();
        }

        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortQuantityCommand);
    }
}
