package seedu.flashback.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.commons.util.AppUtil.checkArgument;

/**
 * Represents a flash card's priority in FlashBack.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priorities can only be High, Mid or Low";

    /*
     * The first character of the priority must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */


    public final String value;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals("High") || test.equals("Mid") || test.equals("Low");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
