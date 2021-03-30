package seedu.address.commons.exportutils.exceptions;

/**
 * Represents an error when exporting a file
 */
public class ExportException extends Exception {
    public ExportException(String message) {
        super(message);
    }
}
