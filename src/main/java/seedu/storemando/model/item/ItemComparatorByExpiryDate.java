package seedu.storemando.model.item;

import java.util.Comparator;

public class ItemComparatorByExpiryDate implements Comparator<Item> {

    @Override
    public int compare(Item firstItem, Item secondItem) {
        if (firstItem.compareByExpiryDate(secondItem) != 0) {
            return firstItem.compareByExpiryDate(secondItem);
        } else if (firstItem.compareByQuantity(secondItem) != 0) {
            return firstItem.compareByQuantity(secondItem);
        } else if (firstItem.compareByLocation(secondItem) != 0) {
            return firstItem.compareByLocation(secondItem);
        } else {
            return firstItem.compareByItemName(secondItem);
        }
    }
}
