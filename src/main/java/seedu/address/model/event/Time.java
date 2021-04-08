package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Time in SOChedule.
 * Guarantees: immutable; time is valid as declared in {@link #isValidTime(String)}.
 */
public class Time implements Comparable<Time> {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter TIME_FORMATTER_STRICT = DateTimeFormatter.ISO_LOCAL_TIME;

    public static final String MESSAGE_CONSTRAINTS =
            "Time should be represented in 24-hour notation, in the format of HH:mm.";

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
        try {
            LocalTime.parse(test, TIME_FORMATTER);
            LocalTime.parse(test, TIME_FORMATTER_STRICT);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
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

    @Override
    public int compareTo(Time other) {
        return this.time.compareTo(other.time);
    }
}
