package seedu.address.model.remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a remark.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}.
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any value, but it should not be blank";

    private static final String VALIDATION_REGEX = "[^\\s].*";

    public final String remark;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        this.remark = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     *
     * @param test The string to test.
     * @return True if the given string is a valid remark, otherwise false.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Remark)) {
            return false;
        }
        Remark otherRemark = (Remark) other;
        return remark.equalsIgnoreCase(otherRemark.remark);
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
