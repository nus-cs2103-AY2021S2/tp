package seedu.iscam.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

/**
 * Represents a meeting's description in the iscam book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Description should be under 1000 characters.\n";
    private static final int CHAR_LIMIT = 1000;

    public final String value;

    /**
     * Constructs an {@code Description}
     *
     * @param desc A valid description
     */
    public Description(String desc) {
        requireNonNull(desc);
        checkArgument(isValidDescription(desc), MESSAGE_CONSTRAINTS);
        value = desc;
    }

    /**
     * Returns if a given string is a valid description
     */
    public static boolean isValidDescription(String desc) {
        return desc.length() <= CHAR_LIMIT;
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
