package seedu.address.model.project.exceptions;

/**
 * Signals that the operation will result in duplicate Deadlines.
 */
public class DuplicateDeadlineException extends RuntimeException {
    public DuplicateDeadlineException() {
        super("Operation would result in duplicate deadlines.");
    }
}
