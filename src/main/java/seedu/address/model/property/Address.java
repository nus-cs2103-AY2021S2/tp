package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Address {
    private static final String MESSAGE_CONSTRAINTS = "Addresses can take any value, but it should not be blank";

    private static final String VALIDATION_REGEX = "[^\\s].*";

    public final String propertyAddress;

    public Address(String propertyAddress) {
        requireNonNull(propertyAddress);
        checkArgument(isValidAddress(propertyAddress), MESSAGE_CONSTRAINTS);
        this.propertyAddress = propertyAddress;
    }

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
        return propertyAddress.equals(otherAddress.propertyAddress);
    }

    @Override
    public int hashCode() {
        return propertyAddress.hashCode();
    }
}
