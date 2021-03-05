package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class Name {
    private static final String MESSAGE_CONSTRAINTS =
            "Property names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the property name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String propertyName;

    /**
     * Constructs a {@code Name}.
     *
     * @param propertyName A valid name.
     */
    public Name(String propertyName) {
        requireNonNull(propertyName);
        checkArgument(isValidName(propertyName), MESSAGE_CONSTRAINTS);
        this.propertyName = propertyName;
    }

    /**
     * Returns true if a given string is a valid property name.
     *
     * @param test The string to test.
     * @return True if the given string is a valid property name, otherwise false.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return propertyName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Name)) {
            return false;
        }
        Name otherName = (Name) other;
        return propertyName.equals(otherName.propertyName);
    }

    @Override
    public int hashCode() {
        return propertyName.hashCode();
    }
}
