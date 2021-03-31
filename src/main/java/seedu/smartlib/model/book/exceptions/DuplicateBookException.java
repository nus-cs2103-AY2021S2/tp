package seedu.smartlib.model.book.exceptions;

/**
 * Signals that the operation will result in duplicate Books (Books are considered duplicates if they have the same
 * identity).
 */
public class DuplicateBookException extends RuntimeException {

    /**
     * A constructor for the DuplicateBookException.
     */
    public DuplicateBookException() {
        super("Operation would result in duplicate books");
    }

}
