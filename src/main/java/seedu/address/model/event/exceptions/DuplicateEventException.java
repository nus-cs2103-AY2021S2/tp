package seedu.address.model.event.exceptions;

public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super("Operation would result in duplicate schedules");
    }
}
