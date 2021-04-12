package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's phone number.
 */
public class ContactPhone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers are 8 digits long, must not start with a 0 and should not have symbols.";

    public static final String VALIDATION_REGEX = "^[1-9]\\d{7}$";

    public final String value;

    /**
     * Creates a ContactPhone with a valid phone number.
     */
    public ContactPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactPhone // instanceof handles nulls
                && value.equals(((ContactPhone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

