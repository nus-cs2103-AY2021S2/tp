package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's preferred mode of contact in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModeOfContact(String)}
 */
public class ModeOfContact {
    public static final String MESSAGE_CONSTRAINTS =
            "Mode of Contact should only be address, phone or email.";
    public static final String VALIDATION_REGEX = "phone|address|email";

    public final String value;

    /**
     * Constructs an {@code ModeOfContact}.
     *
     * @param modeOfContact A valid mode of contact.
     */
    public ModeOfContact(String modeOfContact) {
        assert (modeOfContact != null) : "Mode of contact cannot be empty";
        requireNonNull(modeOfContact);
        checkArgument(isValidModeOfContact(modeOfContact));
        value = modeOfContact;
    }
    public static boolean isValidModeOfContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModeOfContact // instanceof handles nulls
                && value.equals(((ModeOfContact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
