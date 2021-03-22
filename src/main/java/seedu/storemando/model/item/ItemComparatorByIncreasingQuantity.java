package seedu.storemando.model.item;

import java.util.Comparator;

public class ItemComparatorByIncreasingQuantity implements Comparator<Item> {

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
