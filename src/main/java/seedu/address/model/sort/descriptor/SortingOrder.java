package seedu.address.model.sort_descriptor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a value.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortingOrder(String)}.
 */
public class SortingOrder {
    public static final String MESSAGE_CONSTRAINTS = "SortingOrder can take value of either asc or des"
            + ", and it should not be any other values";

    private static final String ASC_ORDER_STRING = "asc";

    private static final String DES_ORDER_STRING = "des";

    public final String value;

    /**
     * Constructs a {@code SortingOrder}.
     *
     * @param value A valid value.
     */
    public SortingOrder(String value) {
        requireNonNull(value);
        checkArgument(isValidSortingOrder(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid value.
     *
     * @param test The string to test.
     * @return True if the given string is a valid value, otherwise false.
     */
    public static boolean isValidSortingOrder(String test) {
        return test.equals(ASC_ORDER_STRING) || test.equals(DES_ORDER_STRING);
    }

    /**
     * Tests whether the sorting order is ascending.
     *
     * @return True if the sorting order is ascending, or otherwise false.
     */
    public boolean isAscendingOrder() {
        return value.equals(ASC_ORDER_STRING);
    }

    /**
     * Tests whether the sorting order is descending.
     *
     * @return True if the sorting order is descending, or otherwise false.
     */
    public boolean isDescendingOrder() {
        return value.equals(DES_ORDER_STRING);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortingOrder)) {
            return false;
        }
        SortingOrder otherSortingOrder = (SortingOrder) other;
        return value.equals(otherSortingOrder.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
