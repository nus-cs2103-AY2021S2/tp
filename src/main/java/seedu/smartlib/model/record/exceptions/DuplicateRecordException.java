package seedu.smartlib.model.record.exceptions;

/**
 * Signals that the operation will result in duplicate Records (Records are considered duplicates if they have the same
 * reader name and book name and dateBorrowed).
 */
public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException() {
        super("Operation would result in duplicate records");
    }
}
