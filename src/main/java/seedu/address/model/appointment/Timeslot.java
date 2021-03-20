package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;

/**
 * Represents the timeslot allocated to an appointment.
 * date time values are truncated to minutes.
 */
public class Timeslot implements Comparable<Timeslot> {
    public static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd MMM yy hh:mm aa");
    public static final String MESSAGE_CONSTRAINTS = "Timeslot end date and time must be strictly "
            + "after the start date and time";

    public final LocalDateTime start;
    public final LocalDateTime end;

    /**
     * Constructor based on specified start and end dateTime.
     * The end dateTime must be strictly larger than the start dateTime.
     * @throws NegativeOrZeroDurationException
     */
    public Timeslot(LocalDateTime start, LocalDateTime end) throws NegativeOrZeroDurationException {
        requireAllNonNull(start, end);

        LocalDateTime truncatedStart = start.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime truncatedEnd = end.truncatedTo(ChronoUnit.MINUTES);

        if (!truncatedEnd.isAfter(truncatedStart)) {
            throw new NegativeOrZeroDurationException();
        }

        this.start = truncatedStart;
        this.end = truncatedEnd;
    }

    /**
     * Constructor based on specified start and duration.
     * The duration must be strictly more than zero seconds.
     * @throws NegativeOrZeroDurationException
     */
    public Timeslot(LocalDateTime start, Duration duration) throws NegativeOrZeroDurationException {
        requireAllNonNull(start, duration);

        LocalDateTime truncatedStart = start.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime truncatedEnd = start.plus(duration).truncatedTo(ChronoUnit.MINUTES);

        if (!truncatedEnd.isAfter(truncatedStart)) {
            throw new NegativeOrZeroDurationException();
        }

        this.start = truncatedStart;
        this.end = truncatedEnd;
    }

    //// Accessors
    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    //// Boolean Checks
    /**
     * Returns true if the dateTime {@code toCheck} lies within the timeslot period.
     */
    public boolean isBetween(LocalDateTime toCheck) {
        return (toCheck.isBefore(getEnd()) || toCheck.isEqual(getEnd()))
                && (toCheck.isAfter(getStart()) || toCheck.isEqual(getStart()));

    }

    /**
     * Returns true if the timeslot {@code otherTimeslot} chronologically overlaps
     * with this timeslot.
     */
    public boolean hasOverlap(Timeslot otherTimeslot) {
        return isBetween(otherTimeslot.getStart()) || isBetween(otherTimeslot.getEnd());
    }

    @Override
    public int compareTo(Timeslot otherTimeslot) {
        int compareStart = getStart().compareTo(otherTimeslot.getStart());

        if (compareStart == 0) {
            return getEnd().compareTo(otherTimeslot.getEnd());
        }

        return compareStart;
    }

    /**
     * Returns true if both timeslots represent the same period.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Timeslot)) {
            return false;
        }

        Timeslot otherTimeslot = (Timeslot) other;
        return otherTimeslot.getStart().equals(getStart())
                && otherTimeslot.getEnd().equals(getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("From ")
                .append(DISPLAY_DATE_TIME_FORMATTER.format(getStart()))
                .append(" To ")
                .append(DISPLAY_DATE_TIME_FORMATTER.format(getEnd()));

        return builder.toString();
    }
}
