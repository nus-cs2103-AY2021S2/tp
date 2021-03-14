package seedu.smartlib.model.book.exceptions;

/**
 * Signals that the operation will result in duplicate Books (Bookss are considered duplicates if they have the same
 * identity).
 */
public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException() {
        super("Operation would result in duplicate books");
    }
}