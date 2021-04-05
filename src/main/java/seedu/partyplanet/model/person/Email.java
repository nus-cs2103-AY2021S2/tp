package seedu.partyplanet.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in PartyPlanet.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String EMPTY_EMAIL_STRING = "";
    public static final Email EMPTY_EMAIL = new Email();

    private static final String SPECIAL_CHARACTERS = "_!#$%&'*+/=?`{|}~^.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format USER@DOMAIN "
            + "and adhere to the following constraints:\n"
            + "1. USER can only contain alphanumerics and any of these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + ") .\n"
            + "2. DOMAIN must comprise at least one non-empty label with an optional trailing period.\n"
            + "3. A label contains at least one of alphanumerics or underscores, with optional hyphens. "
            + "Labels cannot start with a hyphen.";
    // alphanumeric and special characters
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    private static final String DOMAIN_REGEX = "(\\w[\\w-]*\\.?)+$";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;
    private boolean isEmpty = false;

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
     * Constructs an empty Email.
     */
    private Email() {
        value = EMPTY_EMAIL_STRING;
        isEmpty = true;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given email is an empty email.
     */
    public static boolean isEmptyEmail(Email email) {
        return email.isEmpty;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)) // state check
                && isEmpty == ((Email) other).isEmpty; // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
