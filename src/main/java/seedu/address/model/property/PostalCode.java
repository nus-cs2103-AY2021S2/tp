package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's postal code.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostal(String)}.
 */
public class PostalCode implements Comparable<PostalCode> {
    public static final String MESSAGE_CONSTRAINTS = "Note the following conditions for specifying a postal code:\n"
            + "1. Minimum length is 5 digits and maximum length is 10 digits";

    private static final String VALIDATION_REGEX = "\\d{5,10}";

    public final String postal;

    /**
     * Constructs a {@code PostalCode}.
     *
     * @param postal A valid postal.
     */
    public PostalCode(String postal) {
        requireNonNull(postal);
        checkArgument(isValidPostal(postal), MESSAGE_CONSTRAINTS);
        this.postal = postal;
    }

    /**
     * Returns true if a given string is a valid property postal code.
     *
     * @param test The string to test.
     * @return True if the given string is a valid property postal code, otherwise false.
     */
    public static boolean isValidPostal(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return postal;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PostalCode)) {
            return false;
        }
        PostalCode otherPostalCode = (PostalCode) other;
        return postal.equals(otherPostalCode.postal);
    }

    @Override
    public int compareTo(PostalCode another) {
        return this.postal.compareTo(another.postal);
    }

    @Override
    public int hashCode() {
        return postal.hashCode();
    }
}
