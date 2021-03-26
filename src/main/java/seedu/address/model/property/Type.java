package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's type.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}.
 */
public class Type {
    public static final String MESSAGE_CONSTRAINTS =
            "Property types should only be either hdb, condo or landed";
    
    public static final String HDB = "Hdb";
    public static final String CONDO = "Condo";
    public static final String LANDED = "Landed";

    /*
     * The property types should only be one of three types: hdb, condo, or landed.
     */
    private static final String VALIDATION_REGEX = "^(hdb|condo|landed)$";

    private final String propertyType;

    /**
     * Constructs a {@code Type}.
     *
     * @param propertyType A valid type.
     */
    public Type(String propertyType) {
        requireNonNull(propertyType);
        checkArgument(isValidType(propertyType), MESSAGE_CONSTRAINTS);
        this.propertyType = propertyType.substring(0, 1).toUpperCase()
                + propertyType.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid property type.
     *
     * @param test The string to test.
     * @return True if the given string is a valid property type, otherwise false.
     */
    public static boolean isValidType(String test) {
        String lowercaseTest = test.toLowerCase();
        return lowercaseTest.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return propertyType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Type)) {
            return false;
        }
        Type otherType = (Type) other;
        return propertyType.equalsIgnoreCase(otherType.propertyType);
    }

    @Override
    public int hashCode() {
        return propertyType.hashCode();
    }
}
