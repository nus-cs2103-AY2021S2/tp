package seedu.address.model.property.exceptions;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException() {
        super("No such property found.");
    }
}
