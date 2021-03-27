package seedu.address.model.session.exceptions;

/**
 * Signals that the operation will result in invalid SessionId
 * (SessionIds are considered invalid if they are less than or equal to zero.)
 */
public class InvalidSessionIdException extends RuntimeException {
    public InvalidSessionIdException() {
        super("Operation would result in invalid session Id.");
    }
}

