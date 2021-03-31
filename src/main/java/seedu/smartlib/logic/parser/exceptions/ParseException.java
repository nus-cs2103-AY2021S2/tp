package seedu.smartlib.logic.parser.exceptions;

import seedu.smartlib.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    /**
     * A constructor for the ParseException.
     *
     * @param message should contain relevant information on the failed constraint(s).
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * A constructor for the ParseException.
     *
     * @param message should contain relevant information on the failed constraint(s).
     * @param cause of the main exception.
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
