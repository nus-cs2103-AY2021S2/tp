package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's priority in the meeting.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priority can only vary from 1 to 5.";

    public static final String VALIDATION_REGEX = "^[1-5]$";

    public final int priority;


    /**
     * Constructs an {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = Integer.parseInt(priority);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String priority) {
        return priority.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(priority);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.meeting.Priority // instanceof handles nulls
                && priority == (((seedu.address.model.meeting.Priority) other).priority)); // state check
    }


}
