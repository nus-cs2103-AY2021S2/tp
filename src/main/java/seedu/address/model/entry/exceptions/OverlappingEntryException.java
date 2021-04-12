package seedu.address.model.entry.exceptions;

/**
 * Represents an error that occurs when an operation results in the creation of overlapping
 * entries (entries are considered overlapping if their dates clash).
 */
public class OverlappingEntryException extends RuntimeException {
    public OverlappingEntryException() {
        super("Operation would result in overlapping entries");
    }
}
