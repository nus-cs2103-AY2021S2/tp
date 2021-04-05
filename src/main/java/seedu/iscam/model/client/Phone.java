package seedu.iscam.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's phone number in the iscam book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhoneLength(String)}
 */
public class Phone {


    public static final String MESSAGE_LENGTH_CONSTRAINTS = "Phone number should be only 8 digits long.";
    public static final String MESSAGE_INPUT_CONSTRAINTS = "Phone number should only consist of numbers.";
    public static final String MESSAGE_STARTING_DIGIT_CONSTRAINTS = "Phone numbers should start with 6, 8 or 9.";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidNumbersOnly(phone), MESSAGE_INPUT_CONSTRAINTS);
        checkArgument(isValidPhoneLength(phone), MESSAGE_LENGTH_CONSTRAINTS);
        checkArgument(isValidPhoneNumber(phone), MESSAGE_STARTING_DIGIT_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string has a valid phone number length.
     */
    public static boolean isValidPhoneLength(String test) {
        return test.length() == 8;
    }

    /**
     * Returns true if a given string only consists of numbers.
     */
    public static boolean isValidNumbersOnly(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string has valid starting digits.
     */
    public static boolean isValidPhoneNumber(String test) {
        String acceptableFirstDigits = "689";
        String firstDigit = Character.toString(test.charAt(0));
        return acceptableFirstDigits.contains(firstDigit);
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
