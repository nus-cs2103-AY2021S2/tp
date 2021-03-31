package seedu.address.commons.util;

import java.util.List;

public class ListUtil {

    /**
     * Compares two list and returns true if both lists have the same elements (regardless of order)
     */
    public static <U> boolean compareListWithoutOrder(List<U> list, List<U> otherList) {
        return list.containsAll(otherList) && otherList.containsAll(list);
    }

}
