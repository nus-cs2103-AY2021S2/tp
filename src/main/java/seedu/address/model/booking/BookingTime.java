package seedu.address.model.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a BookingTime of a Booking in residence tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class BookingTime {
    private final LocalDate start;
    private final LocalDate end;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    /**
     * Initializes a booking Time
     */
    public BookingTime(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BookingTime)) {
            return false;
        }

        BookingTime otherBookingTime = (BookingTime) other;
        return otherBookingTime.getStart().equals(getStart())
                && otherBookingTime.getEnd().equals(getEnd());
    }

    public static boolean isValidBookingTime(LocalDate start, LocalDate end) {
        return !end.isBefore(start);
    }

    /**
     * Comparator of {@code BookingTime}s by chronological order.
     */
    public int compareTo(BookingTime otherBookingTime) {
        if (this.getStart().isBefore(otherBookingTime.getStart())) {
            return -1;
        } else if (this.getStart().isAfter(otherBookingTime.getStart())) {
            return 1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(start, end);
    }

    /**
     * Returns true if bookingTime overlaps with another bookingTime.
     * This happens if the start and end dates are not
     * before the start date of the other booking time
     * or after the end date of the other booking time.
     */
    public boolean doesOverlap(BookingTime otherBookingTime) {
        boolean isBeforeOtherBooking = this.getEnd().isBefore(otherBookingTime.getStart());
        boolean isAfterOtherBooking = this.getStart().isAfter(otherBookingTime.getEnd());

        return !(isBeforeOtherBooking || isAfterOtherBooking);
    }

}

