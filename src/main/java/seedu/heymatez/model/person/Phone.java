package seedu.heymatez.model.person;

import static java.util.Objects.requireNonNull;

import seedu.heymatez.commons.util.AppUtil;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {
    public static final String MESSAGE_CONSTRAINTS = "Phone numbers should only contain positive numbers, and it should"
            + " be at least 3 digits long with a maximum length of 15 digits";

    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        AppUtil.checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number with 3 digits.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid phone number with a maximum of 15 digits.
     */
    public static boolean isValidLength(String test) {
        return test.length() <= 15;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
