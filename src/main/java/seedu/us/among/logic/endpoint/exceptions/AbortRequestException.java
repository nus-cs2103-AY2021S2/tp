package seedu.us.among.logic.endpoint.exceptions;

import seedu.us.among.logic.endpoint.Request;

/**
 * An exception thrown when user aborts request call during execution of a {@link Request}.
 */
public class AbortRequestException extends Exception {
    public AbortRequestException(String message) {
        super(message);
    }
}
