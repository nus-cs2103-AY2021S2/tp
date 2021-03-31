package seedu.address.model.property.exceptions;

public class DuplicatePropertyException extends RuntimeException {
    public DuplicatePropertyException() {
        super("Operation would result in duplicate properties");
    }
}
