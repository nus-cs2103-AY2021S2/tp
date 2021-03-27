package seedu.address.model.assignment.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents an error encountered when unassigning a tutor.
 */
public class UnassignTutorException extends IllegalValueException {

    public UnassignTutorException(String message) {
        super(message);
    }

    public UnassignTutorException(String message, Throwable cause) {
        super(message, cause);
    }
}
