package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Booking in residence tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking implements Comparable<Booking> {

    public static final String MESSAGE_CONSTRAINTS = "Booking start date must be before the end date";

    private final TenantName tenantName;
    private final Phone phone;
    private final BookingTime bookingTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    /**
     * Every field must be present and not null.
     */
    public Booking(TenantName tenantName, Phone phone, LocalDate start, LocalDate end) {
        requireAllNonNull(tenantName, phone, start, end);
        this.tenantName = tenantName;
        this.phone = phone;
        this.bookingTime = new BookingTime(start, end);
    }

    public TenantName getTenantName() {
        return tenantName;
    }

    public Phone getPhone() {
        return phone;
    }

    public BookingTime getBookingTime() {
        return bookingTime;
    }

    public LocalDate getStart() {
        return bookingTime.getStart();
    }

    public LocalDate getEnd() {
        return bookingTime.getEnd();
    }

    /**
     * Returns if a given booking is a valid booking.
     */
    public static boolean isValidBookingTime(LocalDate start, LocalDate end) {
        return BookingTime.isValidBookingTime(start, end);
    }

    /**
     * Returns true if both bookings have the same name, phone, start and end dates.
     * This defines a stronger notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getTenantName().equals(getTenantName())
                && otherBooking.getPhone().equals(getPhone())
                && otherBooking.getBookingTime().equals(getBookingTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tenantName, phone, bookingTime);
    }

    /**
     * Comparator of bookings to reflect chronological order.
     * Bookings on earlier dates come before booking on later dates.
     */
    @Override
    public int compareTo(Booking otherBooking) {
        return this.bookingTime.compareTo(otherBooking.getBookingTime());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTenantName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Start: ")
                .append(getStart().format(dateTimeFormatter))
                .append("; End: ")
                .append(getEnd().format(dateTimeFormatter));

        return builder.toString();
    }

    /**
     * Returns true if booking time of this Booking overlaps with another Booking's booking time.
     */
    public boolean doesOverlap(Booking otherBooking) {
        return this.getBookingTime().doesOverlap(otherBooking.getBookingTime());
    }

}
