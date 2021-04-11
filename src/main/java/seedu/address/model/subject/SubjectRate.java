package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Subject's hourly rate in Tutor Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidRate(String)}
 */
public class SubjectRate {
    public static final String MESSAGE_CONSTRAINTS =
            "Subject rate should only contain numbers, and it should be at least 1 digit long "
                    + "and at most 5 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,5}";
    public final Integer rate;

    /**
     * Constructs a {@code SubjectRate}.
     *
     * @param rate A valid subject rate.
     */
    public SubjectRate(String rate) {
        requireNonNull(rate);
        checkArgument(isValidRate(rate), MESSAGE_CONSTRAINTS);
        this.rate = Integer.parseInt(rate);
    }

    /**
     * Returns true if a given string is a valid subject rate.
     */
    public static boolean isValidRate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return rate.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectRate // instanceof handles nulls
                && rate.equals(((SubjectRate) other).rate)); // state check
    }

    @Override
    public int hashCode() {
        return rate.hashCode();
    }
}
