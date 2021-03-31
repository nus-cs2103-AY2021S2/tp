package seedu.smartlib.model.reader;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reader's phone number in SmartLib.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";

    public static final String VALIDATION_REGEX = "\\d{3,}";

    private final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     *
     * @param test string to be tested.
     * @return true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns this phone number in String format.
     *
     * @return this phone number in String format.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this phone number is equal to another phone number.
     *
     * @param other the other phone number to be compared.
     * @return true if this phone number is equal to the other phone number, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    /**
     * Generates a hashcode for this Phone.
     *
     * @return the hashcode for this Phone.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
