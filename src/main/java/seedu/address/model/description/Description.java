package seedu.address.model.description;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Description in the wardrobe.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDescriptionName(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description names should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]+$";

    public final String descriptionName;

    /**
     * Constructs a {@code Description}.
     *
     * @param descriptionName A valid description name.
     */
    public Description(String descriptionName) {
        requireNonNull(descriptionName);
        checkArgument(isValidDescriptionName(descriptionName), MESSAGE_CONSTRAINTS);
        this.descriptionName = descriptionName;
    }

    /**
     * Returns true if a given string is a valid description name.
     */
    public static boolean isValidDescriptionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && descriptionName.equals(((Description) other).descriptionName)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + descriptionName + ']';
    }

}
