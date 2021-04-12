package seedu.address.model.date.exceptions;

/**
 * Signals that the operation will result in duplicate ImportantDates (ImportantDates are considered duplicates if they
 * have the same description).
 */
public class DuplicateImportantDateException extends RuntimeException {
    public DuplicateImportantDateException() {
        super("Operation would result in duplicate important dates");
    }
}
