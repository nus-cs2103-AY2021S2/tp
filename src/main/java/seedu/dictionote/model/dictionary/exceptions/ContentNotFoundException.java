package seedu.dictionote.model.dictionary.exceptions;

/**
 * Signals that the operation is unable to find the specified content.
 */
public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException() {
        super("Unfortunately, we weren't able to find any matching results.");
    }
}
