package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Time implements Comparable<Time> {

    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$";
    public static final String MESSAGE_CONSTRAINTS = "Time should be in HHMM format, from 0000 to 2359";
    public final String timeOfTuition;

    /**
     * Constructs a {@code Time}.
     *
     * @param timeOfTuition A valid time.
     */
    public Time(String timeOfTuition) {
        requireNonNull(timeOfTuition);
        checkArgument(isValidTime(timeOfTuition), MESSAGE_CONSTRAINTS);
        this.timeOfTuition = timeOfTuition;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(Time other) {
        int thisTime = Integer.parseInt(timeOfTuition);
        int otherTime = Integer.parseInt(timeOfTuition);
        if (thisTime < otherTime) {
            return -1;
        } else if (thisTime > otherTime) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return timeOfTuition;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && timeOfTuition.equals(((Time) other).timeOfTuition)); // state check
    }

    @Override
    public int hashCode() {
        return timeOfTuition.hashCode();
    }

}
