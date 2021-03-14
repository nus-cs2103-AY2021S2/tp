package seedu.address.model;

public interface Item {
    /**
     * Check if item is same as another item (loose equality)
     * @param other
     * @return result
     */
    public boolean isSame(Item other);
}
