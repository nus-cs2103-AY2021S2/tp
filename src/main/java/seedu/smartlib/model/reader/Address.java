package seedu.smartlib.model.reader;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reader's address in SmartLib.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid address.
     *
     * @param test string to be tested.
     * @return true if a given string is a valid address, and false otherwise.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns this Address in String format.
     *
     * @return this Address in String format.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this Address is equal to another Address.
     *
     * @param other the other Address to be compared.
     * @return true if this Address is equal to the other Address, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    /**
     * Generates a hashcode for this Address.
     *
     * @return the hashcode for this Address.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
