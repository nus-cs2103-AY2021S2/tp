package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's email in Teaching Assistant.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class ContactEmail {

    private static final String PRINTABLE_CHARACTERS = "!#$%&'*+-/=?^_`{|}~";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + PRINTABLE_CHARACTERS + ").\n"
            + "2. The local-part may consist of dots (.) provided that it is not the first character.\n"
            + "3. This is followed by a '@' and then a domain name. "
            + "The domain name must:\n"
            + "    - be at least 2 characters long\n"
            + "    - start and end with alphanumeric characters\n"
            + "    - consist of alphanumeric characters, a period or a hyphen for the characters in between, if any.";

    // local-part validation
    private static final String PARTIAL_LOCAL_PART_REGEX = "\\w" + PRINTABLE_CHARACTERS;
    private static final String LOCAL_PART_REGEX = "^[" + PARTIAL_LOCAL_PART_REGEX + "]["
            + PARTIAL_LOCAL_PART_REGEX + ".]*";

    // domain validation
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "\\w"; // alphanumeric characters except underscore
    private static final String DOMAIN_MIDDLE_REGEX = "[\\w.-]*"; // alphanumeric, period and hyphen
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "\\w$";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
            + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;

    public final String value;

    /**
     * Constructs a {@code ContactEmail}.
     *
     * @param email A valid email address.
     */
    public ContactEmail(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns true if a given string is a valid email.
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
                || (other instanceof ContactEmail // instanceof handles nulls
                && value.equalsIgnoreCase(((ContactEmail) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

