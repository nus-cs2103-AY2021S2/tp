package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}.
 */
public class Address implements Comparable<Address> {
    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any value, but it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[^\\s].*";

    public final String propertyAddress;

    /**
     * Constructs an {@code Address}.
     *
     * @param propertyAddress A valid address.
     */
    public Address(String propertyAddress) {
        requireNonNull(propertyAddress);
        checkArgument(isValidAddress(propertyAddress), MESSAGE_CONSTRAINTS);
        this.propertyAddress = propertyAddress;
    }

    /**
     * Returns true if a given string is a valid property address.
     *
     * @param test The string to test.
     * @return True if the given string is a valid property address, otherwise false.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return propertyAddress;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Address)) {
            return false;
        }
        Address otherAddress = (Address) other;
        return propertyAddress.equalsIgnoreCase(otherAddress.propertyAddress);
    }

    @Override
    public int compareTo(Address another) {
        return this.propertyAddress.compareTo(another.propertyAddress);
    }

    @Override
    public int hashCode() {
        return propertyAddress.hashCode();
    }
}
