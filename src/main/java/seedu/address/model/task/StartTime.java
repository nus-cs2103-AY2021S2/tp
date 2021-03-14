package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's StartTime in the List.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_CONSTRAINTS = "Start Time should be numeric and should be in 24 hours format";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String startTime;
    public final String value;

    /**
     * Constructs an {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        value = startTime;
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidStartTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.startTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.startTime.equals(((StartTime) other).startTime)); // state check
    }

    @Override
    public int hashCode() {
        return this.startTime.hashCode();
    }

}
