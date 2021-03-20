package seedu.booking.model.booking.exceptions;

/**
 * Signals that the operation will result in overlapping Bookings (Bookings are considered overlaps if their booking
 * timing overlaps).
 */
public class OverlappingBookingException extends RuntimeException {
    public OverlappingBookingException() {
        super("Operation would result in overlapping bookings");
    }
}
