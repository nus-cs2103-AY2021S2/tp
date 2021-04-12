package seedu.storemando.model.item.comparator;

import java.util.Comparator;

import seedu.storemando.model.item.Item;

public class ItemComparatorByIncreasingQuantity implements Comparator<Item> {

    /**
     * Compares between 2 item by seeing which item has the higher quantity.
     *
     * @param firstItem The first item being compared.
     * @param secondItem The second item being compared.
     * @return An integer to show which item's quantity is higher.
     */
    @Override
    public int compare(Item firstItem, Item secondItem) {
        if (firstItem.compareByIncreasingQuantity(secondItem) != 0) {
            return firstItem.compareByIncreasingQuantity(secondItem);
        } else if (firstItem.compareByLocation(secondItem) != 0) {
            return firstItem.compareByLocation(secondItem);
        } else {
            return firstItem.compareByItemName(secondItem);
        }
    }
}
