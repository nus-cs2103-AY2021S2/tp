package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.util.DateTimeFormat;

/**
 * Represents an Appointment's meeting date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}.
 */
public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting dates should be valid dates specified strictly in the format DD-MM-YYYY.\n"
            + "E.g. 01-01-2021";

    public static final String MESSAGE_INVALID_DATE = "Invalid date entered.\n"
            + "Please ensure that the appointment date entered is valid";

    public static final String MESSAGE_DATE_OVER = "Appointment date is already over!!!";

    private static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4}";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid meeting date.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Returns true if a given string is in a valid date format.
     *
     * @param test The string to test.
     * @return True if the given string is in a valid date format, otherwise false.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if this {@code Date} has already passed.
     */
    public boolean isOver() {
        return date.compareTo(LocalDate.now()) < 0;
    }

    /**
     * Returns true if this {@code Date} is today's date.
     */
    public boolean isToday() {
        return date.compareTo(LocalDate.now()) == 0;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormat.OUTPUT_DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Date)) {
            return false;
        }
        Date otherDate = (Date) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int compareTo(Date another) {
        return this.date.compareTo(another.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
