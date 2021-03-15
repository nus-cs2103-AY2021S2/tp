package seedu.dictionote.model.dictionary.exceptions;

/**
 * Signals that the operation will result in duplicate Content (Content are considered duplicates if they have the same
 * content).
 */
public class DuplicateDefinitionException extends RuntimeException {
    public DuplicateDefinitionException() {
        super("Operation would result in duplicate definitions");
    }
}
