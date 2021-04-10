package seedu.address.model.entry.exceptions;

public class OverdueEntryException extends RuntimeException {
    public OverdueEntryException() {
        super("Operation would add an overdue entry");
    }
}
