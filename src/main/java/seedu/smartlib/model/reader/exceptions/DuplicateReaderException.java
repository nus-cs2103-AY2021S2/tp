package seedu.smartlib.model.reader.exceptions;

/**
 * Signals that the operation will result in duplicate Readers (Readers are considered duplicates if they have the same
 * identity).
 */
public class DuplicateReaderException extends RuntimeException {

    /**
     * A constructor for the DuplicateReaderException.
     */
    public DuplicateReaderException() {
        super("Operation would result in duplicate readers");
    }

}
