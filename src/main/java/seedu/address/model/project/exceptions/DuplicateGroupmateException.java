package seedu.address.model.project.exceptions;

/**
 * Signals that the operation will result in duplicate Groupmates (Groupmates are considered duplicates if they have
 * the same name).
 */
public class DuplicateGroupmateException extends RuntimeException {
    public DuplicateGroupmateException() {
        super("Operation would result in duplicate groupmates");
    }
}
