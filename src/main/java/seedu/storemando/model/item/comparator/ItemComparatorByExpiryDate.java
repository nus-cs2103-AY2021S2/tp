package seedu.storemando.model.item.comparator;

import java.util.Comparator;

import seedu.storemando.model.item.Item;

public class ItemComparatorByExpiryDate implements Comparator<Item> {

    /**
     * Compares between 2 ExpiryDate objects by seeing which item expires earlier.
     *
     * @param firstItem The first item being compared.
     * @param secondItem The second item being compared.
     * @return An integer to show which ExpiryDate is greater.
     */
    @Override
    public int compare(Item firstItem, Item secondItem) {
        if (firstItem.compareByExpiryDate(secondItem) != 0) {
            return firstItem.compareByExpiryDate(secondItem);
        } else if (firstItem.compareByIncreasingQuantity(secondItem) != 0) {
            return firstItem.compareByIncreasingQuantity(secondItem);
        } else if (firstItem.compareByLocation(secondItem) != 0) {
            return firstItem.compareByLocation(secondItem);
        } else {
            return firstItem.compareByItemName(secondItem);
        }
    }
}
