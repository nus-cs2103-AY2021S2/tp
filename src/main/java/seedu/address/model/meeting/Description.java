package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Meeting's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {
    public final String value;

    /**
     * Constructs an {@code Description}
     * @param desc A valid description
     */
    public Description(String desc) {
        requireNonNull(desc);
        //checkArguments
        value = desc;
    }

    /**
     * Returns if a given string is a valid description
     */
    public static boolean isValidDescription(String desc) {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Description && value.equals(((Description) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
