package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation will result in overlapping Bookings (Bookings are considered overlapping if they have
 * overlapping start and end dates).
 */
public class OverlappingBookingException extends RuntimeException {
    public OverlappingBookingException() {
        super("Operation would result in overlapping bookings");
    }
}
