package seedu.cakecollate.logic.parser.exceptions;


/**
 * Represents a parse error encountered by a parser, when the index provided is a negative integer.
 */
public class NegativeIndexException extends ParseException {

    public NegativeIndexException(String message) {
        super(message);
    }
}
