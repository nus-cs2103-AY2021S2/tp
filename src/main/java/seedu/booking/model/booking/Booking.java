package seedu.booking.model.booking;

import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import seedu.booking.model.Tag;
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
    private Email bookerEmail;
    private VenueName venueName;
    private final Description description;
    private final StartTime bookingStart;
    private final EndTime bookingEnd;
    private final Id id;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     * Booking id is provided.
     */
    public Booking(Email bookerEmail, VenueName venueName, Description description,
                   StartTime bookingStart, EndTime bookingEnd, Set<Tag> tags, Id id) {
        requireAllNonNull(bookerEmail, venueName, description, bookingStart, bookingEnd, tags);
        this.bookerEmail = bookerEmail;
        this.venueName = venueName;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.tags.addAll(tags);
        this.id = id;
    }

    /**
     * Every field must be present and not null.
     * Booking id is not provided.
     */
    public Booking(Email bookerEmail, VenueName venueName, Description description,
                   StartTime bookingStart, EndTime bookingEnd, Set<Tag> tags) {
        requireAllNonNull(bookerEmail, venueName, description, bookingStart, bookingEnd, tags);
        this.bookerEmail = bookerEmail;
        this.venueName = venueName;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.tags.addAll(tags);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Id getId() {
        return id;
    }

    public static Id getNewBookingId() {
        int number = BOOKING_RANDOM.nextInt(9999);
        String fourDigitNumber = String.format("%04d", number);
        return new Id(fourDigitNumber);
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
        if (otherBooking.getId().equals(getId())) {
            return false;
        }
        if (otherBooking.getVenueName().equals(this.venueName)) {
            return ((this.bookingStart.value.isAfter(otherBooking.bookingStart.value)
                            || this.bookingStart.value.equals(otherBooking.bookingStart.value))
                        && (this.bookingStart.value.isBefore(otherBooking.bookingEnd.value)
                            || this.bookingStart.value.equals(otherBooking.bookingEnd.value)))
                    || ((this.bookingEnd.value.isAfter(otherBooking.bookingStart.value)
                            || this.bookingEnd.value.equals(otherBooking.bookingStart.value))
                        && (this.bookingEnd.value.isBefore(otherBooking.bookingEnd.value)
                            || this.bookingEnd.value.equals(otherBooking.bookingEnd.value)));
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
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

    /**
     * Returns true if both bookings have same fields.
     * This defines a weaker notion of equality between two bookings.
     */
    public boolean isExactlySameBooking(Booking otherBooking) {
        return otherBooking.getBookerEmail().equals(getBookerEmail())
                && otherBooking.getVenueName().equals(getVenueName())
                && otherBooking.getBookingStart().equals(getBookingStart())
                && otherBooking.getBookingEnd().equals(getBookingEnd())
                && otherBooking.getDescription().equals(getDescription())
                && otherBooking.getTags().equals(getTags());
    }

    public void setVenueName(VenueName venueName) {
        this.venueName = venueName;
    }

    public void setEmail(Email newEmail) {
        this.bookerEmail = newEmail;
    }
}
