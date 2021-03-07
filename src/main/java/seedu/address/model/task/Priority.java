package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority should be represented as an integer from 0 to 9";
    public static final String VALIDATION_REGEX = "^[0-9]$";

    private int priority;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priorityString A valid priority.
     */
    public Priority(String priorityString) {
        requireNonNull(priorityString);
        checkArgument(isValidPriority(priorityString), MESSAGE_CONSTRAINTS);
        priority = Integer.parseInt(priorityString);
    }

    public int getPriority() {
        return this.priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return Integer.toString(this.priority);
    }
}
