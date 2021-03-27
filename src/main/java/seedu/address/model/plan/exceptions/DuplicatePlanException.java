package seedu.address.model.plan.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePlanException extends RuntimeException {
    public DuplicatePlanException() {
        super("Operation would result in duplicate persons");
    }
}
