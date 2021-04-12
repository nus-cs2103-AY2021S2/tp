package seedu.storemando.model.item.comparator;

import java.util.Comparator;

import seedu.storemando.model.item.Item;

public class ItemComparatorByDecreasingQuantity implements Comparator<Item> {

    /**
     * Compares between 2 item by seeing which item has the lower quantity.
     * @param firstItem The first item being compared.
     * @param secondItem The second item being compared.
     * @return An integer to show which item's quantity is lesser.
     */
    @Override
    public int compare(Item firstItem, Item secondItem) {
        if (firstItem.compareByDecreasingQuantity(secondItem) != 0) {
            return firstItem.compareByDecreasingQuantity(secondItem);
        } else if (firstItem.compareByLocation(secondItem) != 0) {
            return firstItem.compareByLocation(secondItem);
        } else {
            return firstItem.compareByItemName(secondItem);
        }
    }
}
