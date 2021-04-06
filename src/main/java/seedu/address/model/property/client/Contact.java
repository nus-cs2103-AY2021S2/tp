package seedu.address.model.property.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a client's contact number.
 * Guarantees: immutable; is valid as declared in {@link #isValidContact(String)}.
 */
public class Contact {
    public static final String MESSAGE_CONSTRAINTS = "Note the following conditions for specifying a contact number:\n"
            + "1. The starting plus sign (+) is optional.\n"
            + "2. Minimum length is 7 digits and maximum length is 15 digits";

    public static final String VALIDATION_REGEX = "\\+?\\d{7,15}";

    public final String contact;

    /**
     * Constructs a {@code Contact}.
     *
     * @param contact A valid contact number.
     */
    public Contact(String contact) {
        requireNonNull(contact);
        checkArgument(isValidContact(contact), MESSAGE_CONSTRAINTS);
        this.contact = contact;
    }

    /**
     * Returns true if a given string is a valid contact number.
     *
     * @param test The string to test.
     * @return True if the given string is a valid contact number, otherwise false.
     */
    public static boolean isValidContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return contact;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Contact)) {
            return false;
        }
        Contact otherContact = (Contact) other;
        return contact.equals(otherContact.contact);
    }

    @Override
    public int hashCode() {
        return contact.hashCode();
    }
}
