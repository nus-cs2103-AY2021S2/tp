package seedu.taskify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {


    public static final String MESSAGE_CONSTRAINTS =
            "Description can take any values but it should not have more than "
            + "80 characters and should not be blank.";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description number.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid description and has length less
     * than or equal to 80 characters
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 80;
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
