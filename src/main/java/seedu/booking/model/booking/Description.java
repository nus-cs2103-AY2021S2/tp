package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

/**
 * Represents a description in the booking system.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description cannot be empty.";
    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        String trimmedTest = test.trim();
        return !trimmedTest.isEmpty();
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
