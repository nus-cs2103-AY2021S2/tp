package seedu.taskify.logic.parser.exceptions;

import seedu.taskify.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error during parsing.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
