package seedu.iscam.logic.parser.exceptions;

public class ParseFormatException extends ParseException {
    public ParseFormatException(String message) {
        super(message);
    }

    public ParseFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
