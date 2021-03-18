package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Priority in SOChedule.
 * Guarantees: immutable; priority is valid as declared in {@link #isValidPriority(String)}.
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority should be represented as an integer from 0 to 9";
    public static final String VALIDATION_REGEX = "^[0-9]$";
    public static final int DEFAULT_PRIORITY = 5;

    public final int priority;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priorityString A valid priority.
     */
    public Priority(String priorityString) {
        if (priorityString == null) {
            priority = DEFAULT_PRIORITY;
        } else {
            checkArgument(isValidPriority(priorityString), MESSAGE_CONSTRAINTS);
            priority = Integer.parseInt(priorityString);
        }
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test == null || test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && priority == ((Priority) other).priority); // state check
    }

    @Override
    public int hashCode() {
        return priority;
    }

    @Override
    public String toString() {
        return Integer.toString(this.priority);
    }
}
