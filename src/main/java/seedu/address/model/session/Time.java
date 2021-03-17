package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Wrapper class for LocalTime.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {
    public static final String TIME_FORMAT = "HH:mm";
    public static final String MESSAGE_CONSTRAINTS = "Time must be in the format: " + TIME_FORMAT;
    public static final String VALIDATION_REGEX = "^(2[0-3]|[01][0-9]):([0-5][0-9])$"; // 24-hour HH:mm format

    private LocalTime time;

    /**
     * Constructs an {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time, getFormatter());
    }

    /**
     * Returns if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(TIME_FORMAT);
    }

    public boolean isSame(Time other) {
        return this.time.equals(other.time);
    }

    public boolean isBefore(Time other) {
        return this.time.isBefore(other.time);
    }

    public boolean isAfter(Time other) {
        return this.time.isAfter(other.time);
    }

    public String toString() {
        return this.time.toString();
    }
}
