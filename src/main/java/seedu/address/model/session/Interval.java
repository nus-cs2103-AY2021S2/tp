package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the interval, as number of days, between each session of the recurring session,
 * Guarantees: immutable; is valid as declared in {@link #isValidInterval(String)}
 */
public class Interval {

    public static final String MESSAGE_CONSTRAINTS = "Interval cannot be empty, negative or 0.";
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";
    public final int value;

    /**
     * Constructs a {@code Interval}.
     *
     * @param interval A valid interval.
     */
    public Interval(String interval) {
        requireNonNull(interval);
        checkArgument(isValidInterval(interval), MESSAGE_CONSTRAINTS);
        value = Integer.valueOf(interval);
    }

    /**
     * Returns true if a given interval int is valid.
     */
    public static boolean isValidInterval(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interval // instanceof handles nulls
                && value == (((Interval) other).value)); // state check
    }
}
