package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.tutor.Address;

public class AddressFilter implements Predicate<Address> {
    public static final String MESSAGE_CONSTRAINTS =
            "Addresses can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String addressFilter;

    /**
     * Constructs a {@code AddressFilter}.
     *
     * @param addressFilter A valid address filter.
     */
    public AddressFilter(String addressFilter) {
        requireNonNull(addressFilter);
        checkArgument(isValidAddressFilter(addressFilter), MESSAGE_CONSTRAINTS);
        this.addressFilter = addressFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid address filter.
     */
    public static boolean isValidAddressFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Address: " + addressFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressFilter // instanceof handles nulls
                && addressFilter.equals(((AddressFilter) other).addressFilter)); // state check
    }

    @Override
    public int hashCode() {
        return addressFilter.hashCode();
    }

    @Override
    public boolean test(Address address) {
        if (address == null) {
            return false;
        }

        return address.value.toLowerCase().contains(addressFilter);
    }
}
