package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;

import seedu.address.model.util.DateTimeFormat;

/**
 * Represents an Appointment's meeting time.
 * Guarantees: immutable.
 */
public class Time implements Comparable<Time> {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting times should be valid times specified in 24-hour clock in the format HHMM";

    public final LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid meeting time.
     */
    public Time(LocalTime time) {
        requireNonNull(time);
        this.time = time;
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormat.OUTPUT_TIME_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Time)) {
            return false;
        }
        Time otherTime = (Time) other;
        return time.equals(otherTime.time);
    }

    @Override
    public int compareTo(Time another) {
        return this.time.compareTo(another.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
