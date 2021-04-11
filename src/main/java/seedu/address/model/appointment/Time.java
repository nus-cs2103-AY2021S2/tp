package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;

import seedu.address.model.util.DateTimeFormat;

/**
 * Represents an Appointment's meeting time.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}.
 */
public class Time implements Comparable<Time> {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting times should be valid times specified in 24-hour clock in the format HHMM.\n"
            + "E.g. 0930, 2359\n";

    public static final String MESSAGE_INVALID_TIME = "Invalid time entered.\n"
            + "Please ensure that the appointment time entered is valid\n"
            + "Note: 2400 is not a valid time. Please enter 0000 instead.";

    public static final String MESSAGE_TIME_OVER = "Appointment time is already over!!!";

    private static final String VALIDATION_REGEX = "\\d{4}";

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

    /**
     * Returns true if a given string is in a valid time format.
     *
     * @param test The string to test.
     * @return True if the given string is in a valid time format, otherwise false.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if this {@code Time} has already passed.
     */
    public boolean isOver() {
        return time.compareTo(LocalTime.now()) <= 0;
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
