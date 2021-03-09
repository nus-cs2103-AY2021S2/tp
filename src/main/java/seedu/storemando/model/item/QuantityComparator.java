package seedu.storemando.model.item;

import java.util.Comparator;

public class QuantityComparator implements Comparator<Item> {

    @Override
    public int compare(Item firstItem, Item secondItem) {
        int firstItemQuantity = Integer.parseInt(firstItem.getQuantity().value);
        int secondItemQuantity = Integer.parseInt(secondItem.getQuantity().value);
        String firstItemLocation = firstItem.getLocation().value;
        String secondItemLocation = secondItem.getLocation().value;
        String firstItemName = firstItem.getItemName().fullName;
        String secondItemName = secondItem.getItemName().fullName;

        if (firstItemQuantity > secondItemQuantity) {
            return 1;
        } else if (firstItemQuantity < secondItemQuantity) {
            return -1;
        } else if (firstItemLocation.compareTo(secondItemLocation) > 0) {
            return 1;
        } else if (firstItemLocation.compareTo(secondItemLocation) < 0) {
            return -1;
        } else if (firstItemName.compareTo(secondItemName) > 0) {
            return 1;
        } else if (firstItemName.compareTo(secondItemName) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
