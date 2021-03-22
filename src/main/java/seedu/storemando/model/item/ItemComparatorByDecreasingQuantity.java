package seedu.storemando.model.item;

import java.util.Comparator;

public class ItemComparatorByDecreasingQuantity implements Comparator<Item> {

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
