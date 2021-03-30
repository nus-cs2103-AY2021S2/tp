package seedu.address.model.tutor.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTutorException extends RuntimeException {
    public DuplicateTutorException() {
        super("Operation would result in duplicate persons");
    }
}
