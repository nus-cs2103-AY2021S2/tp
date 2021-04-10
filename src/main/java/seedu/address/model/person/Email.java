package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = ".!#$%&'*+/=?`{|}~^.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@labels "
            + "and adhere to the following constraints:\n"
            + "1. The local-part can contain any characters provided that they are either:\n"
            + "Alphanumeric (A - Z, a - z, 0 - 9), or any character from the set (" + SPECIAL_CHARACTERS + "), "
            + "excluding the parentheses.\n"
            + "2. This is followed by a '@' and then one or more label. "
            + "A label must only contain alphanumeric characters and be shorter than 64 characters. \n"
            + "3. Multiple labels are allowed, provided that they are delimited by a single period. \n"
            + "Example: JohnDoe@example.com.sg";
    private static final String VALIDATION_REGEX = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@"
            + "[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?"
            + "(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    public final String value;

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
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
