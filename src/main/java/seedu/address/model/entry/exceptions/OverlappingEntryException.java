package seedu.address.model.entry.exceptions;

public class OverlappingEntryException extends RuntimeException {
    public OverlappingEntryException() {
        super("Operation would result in overlapping entries");
    }
}
