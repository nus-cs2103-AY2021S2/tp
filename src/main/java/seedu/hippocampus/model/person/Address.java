package seedu.hippocampus.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.hippocampus.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in HippoCampus.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String EMPTY_ADDRESS_STRING = "";
    public static final Address EMPTY_ADDRESS = new Address();
    public final String value;
    private boolean isEmpty = false;

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
     * Constructs an empty Address.
     */
    private Address() {
        value = EMPTY_ADDRESS_STRING;
        isEmpty = true;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given address is an empty address.
     */
    public static boolean isEmptyAddress(Address address) {
        return address.isEmpty;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)) // state check
                && isEmpty == ((Address) other).isEmpty; // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
