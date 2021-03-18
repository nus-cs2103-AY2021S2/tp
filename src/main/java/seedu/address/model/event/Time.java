package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Time in SOChedule.
 * Guarantees: immutable; time is valid as declared in {@link #isValidTime(String)}.
 */
public class Time {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be represented in 24-hour notation, in the format of HH:mm";
    public static final String VALIDATION_REGEX = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$";

    public final LocalTime time;


    /**
     * Constructs an {@code Time}.
     *
     * @param timeString A valid time.
     */
    public Time(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        time = LocalTime.parse(timeString, TIME_FORMATTER);
    }

    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    /**
     * Returns the time in a string.
     */
    public String toString() {
        return this.time != null ? this.time.format(TIME_FORMATTER) : "";
    }
}
