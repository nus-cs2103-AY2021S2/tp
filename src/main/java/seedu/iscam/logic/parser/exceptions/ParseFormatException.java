package seedu.iscam.logic.parser.exceptions;

import seedu.iscam.commons.exceptions.IllegalValueException;

public class ParseFormatException extends ParseException {
    public ParseFormatException(String message) {
        super(message);
    }

    public ParseFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
