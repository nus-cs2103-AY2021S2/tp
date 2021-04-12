package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's name.
 */
public class ContactName {

    public static final String MESSAGE_CONSTRAINTS = "The name must:\n"
            + "- be at least 2 characters long\n"
            + "- consist of only alphabets, spaces and hyphens\n"
            + "- start and end with alphabets";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z][-A-Za-z ]*[A-Za-z]$";

    public final String fullName;

    /**
     * Creates a ContactName with a vaild name.
     */
    public ContactName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactName // instanceof handles nulls
                && fullName.equalsIgnoreCase(((ContactName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}

