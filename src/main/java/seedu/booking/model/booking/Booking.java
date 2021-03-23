package seedu.booking.model.booking;

import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Random;

import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

/**
 * Represents a booking in the booking list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {
    // Random object used to generate booking id.
    private static final Random BOOKING_RANDOM = new Random();

    // Data fields
    private final Email bookerEmail;
    private final VenueName venueName;
    private final Description description;
    private final StartTime bookingStart;
    private final EndTime bookingEnd;
    private final Id id;

    /**
     * Every field must be present and not null.
     * Booking id is provided.
     */
    public Booking(Email bookerEmail, VenueName venueName, Description description,
                   StartTime bookingStart, EndTime bookingEnd, Id id) {
        requireAllNonNull(bookerEmail, venueName, description, bookingStart, bookingEnd);
        this.bookerEmail = bookerEmail;
        this.venueName = venueName;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.id = id;
    }

    /**
     * Every field must be present and not null.
     * Booking id is not provided.
     */
    public Booking(Email bookerEmail, VenueName venueName, Description description,
                   StartTime bookingStart, EndTime bookingEnd) {
        requireAllNonNull(bookerEmail, venueName, description, bookingStart, bookingEnd);
        this.bookerEmail = bookerEmail;
        this.venueName = venueName;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.id = getNewBookingId();
    }

    public Email getBookerEmail() {
        return bookerEmail;
    }

    public VenueName getVenueName() {
        return venueName;
    }

    public Description getDescription() {
        return description;
    }

    public StartTime getBookingStart() {
        return bookingStart;
    }

    public EndTime getBookingEnd() {
        return bookingEnd;
    }

    public Id getId() {
        return id;
    }

    public static Id getNewBookingId() {
        return new Id(Math.abs(BOOKING_RANDOM.nextInt()));
    }

    /**
     * Returns true if both bookings overlap.
     * This can be used to test for booking conflicts.
     */
    public boolean isOverlapping(seedu.booking.model.booking.Booking otherBooking) {
        if (otherBooking == this) {
            return true;
        }
        if (otherBooking == null) {
            return false;
        }
        if (otherBooking.getVenueName().equals(this.venueName)) {
            return this.bookingStart.value.compareTo(otherBooking.bookingEnd.value) < 0
                    && this.bookingEnd.value.compareTo(otherBooking.bookingStart.value) > 0;
        } else {
            return false;
        }

    }

    /**
     * Returns true if it is the same id.
     */
    public boolean isId(Id id) {
        return this.id.value.equals(id.value);
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

        if (!(other instanceof seedu.booking.model.booking.Booking)) {
            return false;
        }

        seedu.booking.model.booking.Booking otherBooking = (seedu.booking.model.booking.Booking) other;
        return otherBooking.getBookerEmail().equals(getBookerEmail())
                && otherBooking.getVenueName().equals(getVenueName())
                && otherBooking.getDescription().equals(getDescription())
                && otherBooking.getBookingStart().equals(getBookingStart())
                && otherBooking.getBookingEnd().equals(getBookingEnd());
    }

    /**
     * Returns true if the start time is earlier than the end time.
     */
    public boolean isValidTime() {
        return this.bookingStart.value.compareTo(this.bookingEnd.value) < 0;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(bookerEmail, venueName, description, bookingStart, bookingEnd, id);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Booker: ")
                .append(getBookerEmail())
                .append("; Venue: ")
                .append(getVenueName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Start of booking: ")
                .append(getBookingStart())
                .append("; End of booking: ")
                .append(getBookingEnd())
                .append("; ID: ")
                .append(getId());

        return builder.toString();
    }

    /**
     * Returns true if both bookings have the same id.
     * This defines a weaker notion of equality between two bookings.
     */
    public boolean isSameBooking(Booking otherBooking) {
        if (otherBooking == this) {
            return true;
        }

        return otherBooking != null
                && otherBooking.getId().equals(getId());
    }
}
