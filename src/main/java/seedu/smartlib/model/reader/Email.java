package seedu.smartlib.model.reader;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reader's email in SmartLib.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + ") .\n"
            + "2. This is followed by a '@' and then a domain name. "
            + "The domain name must:\n"
            + "    - be at least 2 characters long\n"
            + "    - start and end with alphanumeric characters\n"
            + "    - consist of alphanumeric characters, a period or a hyphen for the characters in between, if any.";
    // alphanumeric and special characters
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]"; // alphanumeric characters except underscore
    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*"; // alphanumeric, period and hyphen
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
            + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;

    private final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns true if a given string is a valid email.
     *
     * @param test string to be tested.
     * @return true if a given string is a valid email, and false otherwise.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns this Email in String format.
     *
     * @return this Email in String format.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this Email is equal to another Email.
     *
     * @param other the other Email to be compared.
     * @return true if this Email is equal to the other Email, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    /**
     * Generates a hashcode for this Email.
     *
     * @return the hashcode for this Email.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
