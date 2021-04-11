package seedu.address.model.pool.exceptions;

/**
 * Signals that the operation will result in duplicate Passengers (Passengers are considered duplicates if they have the
 * same identity).
 */
public class DuplicatePoolException extends RuntimeException {
    public DuplicatePoolException() {
        super("Operation would result in duplicate pools");
    }
}
