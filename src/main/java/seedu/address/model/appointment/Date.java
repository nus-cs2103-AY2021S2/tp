package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.util.DateTimeFormat;

/**
 * Represents an Appointment's meeting date.
 * Guarantees: immutable.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting dates should be valid dates specified in the format DD-MM-YY.";

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
    public int hashCode() {
        return date.hashCode();
    }
}
