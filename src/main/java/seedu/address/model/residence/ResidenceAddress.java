package seedu.address.model.residence;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Residence's address in ResidenceTracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidResidenceAddress(String)}
 */
public class ResidenceAddress {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the Residence's address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ResidenceAddress}.
     *
     * @param address A valid address.
     */
    public ResidenceAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidResidenceAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid address.
     */
    public static boolean isValidResidenceAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns value of this {@code ResidenceAddress}.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidenceAddress // instanceof handles nulls
                && value.equals(((ResidenceAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
