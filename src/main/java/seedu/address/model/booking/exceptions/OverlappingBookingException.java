package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation will result in overlapping Bookings for a Residence
 * (Bookings are considered overlapping if they have overlapping booking times).
 */
public class OverlappingBookingException extends RuntimeException {
    public OverlappingBookingException() {
        super("Operation would result in overlapping bookings");
    }
}
