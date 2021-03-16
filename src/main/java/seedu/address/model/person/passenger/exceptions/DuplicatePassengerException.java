package seedu.address.model.person.passenger.exceptions;

/**
 * Signals that the operation will result in duplicate Passengers (Passengers are considered duplicates if they have the
 * same identity).
 */
public class DuplicatePassengerException extends RuntimeException {
    public DuplicatePassengerException() {
        super("Operation would result in duplicate passengers");
    }
}
