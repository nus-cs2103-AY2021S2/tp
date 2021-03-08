package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a booking in the booking list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    // Data fields
    private final String booker;
    private final Venue venue;
    private final String description;
    private final LocalDateTime bookingStart;
    private final LocalDateTime bookingEnd;

    /**
     * Every field must be present and not null.
     */
    public Booking(String booker, Venue venue, String description,
                   LocalDateTime bookingStart, LocalDateTime bookingEnd) {
        requireAllNonNull(booker, venue, description, bookingStart, bookingEnd);
        this.booker = booker;
        this.venue = venue;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
    }

    public String getBooker() {
        return booker;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getBookingStart() {
        return bookingStart;
    }

    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }

    /**
     * Returns true if both bookings overlap.
     * This can be used to test for booking conflicts.
     */
    public boolean isOverlapping(seedu.address.model.booking.Booking otherBooking) {
        if (otherBooking == this) {
            return false;
        }
        if (otherBooking == null) {
            return false;
        }
        if (!otherBooking.venue.equals(venue)) {
            return false;
        }
        return !this.bookingStart.isBefore(otherBooking.bookingEnd)
                && !this.bookingEnd.isAfter(otherBooking.bookingStart);
    }

    /**
     * Returns true if both bookings have the same data fields.
     * This notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.booking.Booking)) {
            return false;
        }

        seedu.address.model.booking.Booking otherBooking = (seedu.address.model.booking.Booking) other;
        return otherBooking.getBooker().equals(getBooker())
                && otherBooking.getVenue().equals(getVenue())
                && otherBooking.getDescription().equals(getDescription())
                && otherBooking.getBookingStart().equals(getBookingStart())
                && otherBooking.getBookingEnd().equals(getBookingEnd());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(booker, booker, bookingStart, bookingEnd);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Booker: ")
                .append(getBooker())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Description: ")
                .append(getDescription())
                .append("; Start of booking: ")
                .append(getBookingStart())
                .append("; End of booking: ")
                .append(getBookingEnd());

        return builder.toString();
    }

}
