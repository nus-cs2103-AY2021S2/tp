package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation will result in duplicate Bookings (Bookings are considered duplicates if they have the
 * same identity).
 */
public class DuplicateBookingException extends RuntimeException {
    public DuplicateBookingException() {
        super("Operation would result in duplicate bookings");
    }
}
