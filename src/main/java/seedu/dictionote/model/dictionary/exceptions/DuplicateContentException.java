package seedu.dictionote.model.dictionary.exceptions;

/**
 * Signals that the operation will result in duplicate Content (Content are considered duplicates if they have the same
 * content).
 */
public class DuplicateContentException extends RuntimeException {
    public DuplicateContentException() {
        super("Operation would result in duplicate content");
    }
}
