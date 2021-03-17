package seedu.address.model;

import static seedu.address.commons.util.AppUtil.checkArgument;

public abstract class AbstractId<U> implements Comparable<AbstractId> {
    public static final String MESSAGE_CONSTRAINTS = "ID must be a positive number.";

    public final int value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    public AbstractId(int id) {
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns if a given integer is a valid quantity.
     */
    public static boolean isValidId(int id) {
        return id > 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AbstractId // instanceof handles nulls
            && value == ((AbstractId) other).value); // state check
    }

    @Override
    public int compareTo(AbstractId id) {
        if (value > id.value) {
            return 1;
        } else if (value < id.value) {
            return -1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
