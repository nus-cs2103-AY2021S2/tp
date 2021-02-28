package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Address.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Address.VALIDATION_REGEX;

public class Address {

    public final String address;

    /**
     * Constructs a {@code Tag}.
     *
     * @param address A valid address tag.
     */

    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        this.address = address;
    }

    /**
     * Returns true if a given string is a valid tag location.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tag.Address // instanceof handles nulls
                && address.equals(((seedu.address.model.tag.Address) other).address)); // state check
    }
}
