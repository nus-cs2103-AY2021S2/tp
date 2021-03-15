package seedu.address.model.sortDescriptor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a sortingOrder.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortingOrder(String)}.
 */
public class SortingOrder {
    public static final String MESSAGE_CONSTRAINTS = "SortingOrder can take value of either asc or des" +
            ", and it should not be any other values";

    private static final String VALIDATION_REGEX = "asc|des";

    public final String sortingOrder;

    /**
     * Constructs a {@code SortingOrder}.
     *
     * @param sortingOrder A valid sortingOrder.
     */
    public SortingOrder(String sortingOrder) {
        requireNonNull(sortingOrder);
        checkArgument(isValidSortingOrder(sortingOrder), MESSAGE_CONSTRAINTS);
        this.sortingOrder = sortingOrder;
    }

    /**
     * Returns true if a given string is a valid sortingOrder.
     *
     * @param test The string to test.
     * @return True if the given string is a valid sortingOrder, otherwise false.
     */
    public static boolean isValidSortingOrder(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return sortingOrder;
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
        return sortingOrder.equals(otherSortingOrder.sortingOrder);
    }

    @Override
    public int hashCode() {
        return sortingOrder.hashCode();
    }
}
