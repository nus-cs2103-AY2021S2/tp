package seedu.booking.model.venue.exceptions;

/**
 * Signals that the operation will result in duplicate Venues (Venues are considered duplicates if they have the same
 * data).
 */
public class DuplicateVenueException extends RuntimeException {
    public DuplicateVenueException() {
        super("Operation would result in duplicate venues");
    }
}
