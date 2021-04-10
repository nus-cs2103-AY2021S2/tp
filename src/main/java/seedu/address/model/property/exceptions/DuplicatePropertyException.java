package seedu.address.model.property.exceptions;

/**
 * Signals that the operation will result in duplicate Properties
 * (Properties are considered duplicates if they have the pair of address and postal code).
 */
public class DuplicatePropertyException extends RuntimeException {

    public DuplicatePropertyException() {
        super("Operation would result in duplicate properties");
    }
}
