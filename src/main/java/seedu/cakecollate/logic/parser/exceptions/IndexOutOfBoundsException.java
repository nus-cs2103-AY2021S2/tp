package seedu.cakecollate.logic.parser.exceptions;


/**
 * Represents a parse error encountered by a parser, when the index provided is an integer above 32bits.
 */
public class IndexOutOfBoundsException extends ParseException {

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
