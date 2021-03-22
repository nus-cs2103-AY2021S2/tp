package seedu.address.model.event.exceptions;

public class DuplicateGeneralEventException extends RuntimeException {
    public DuplicateGeneralEventException() {
        super("Operation would result in duplicate General Events");
    }
}
