package seedu.us.among.model.endpoint.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateApiEndpointException extends RuntimeException {
    public DuplicateApiEndpointException() {
        super("Operation would result in duplicate endpoints.");
    }
}
