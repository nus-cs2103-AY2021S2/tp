package seedu.us.among.logic.request.exceptions;

import seedu.us.among.logic.request.Request;

/**
 * Represents an error which occurs during execution of a {@link Request}.
 */
public class RequestException extends Exception {
    public RequestException(String message) {
        super(message);
    }
}
