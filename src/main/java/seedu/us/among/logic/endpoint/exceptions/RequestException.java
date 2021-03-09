package seedu.us.among.logic.endpoint.exceptions;

import seedu.us.among.logic.endpoint.Request;

/**
 * Represents an error which occurs during execution of a {@link Request}.
 */
public class RequestException extends Exception {
    public RequestException(String message) {
        super(message);
    }
}
