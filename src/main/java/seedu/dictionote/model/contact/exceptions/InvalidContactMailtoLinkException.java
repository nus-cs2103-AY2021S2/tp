package seedu.dictionote.model.contact.exceptions;
/**
 * Signals that the provided {@code mailto} link for invoking the OS's mail client is invalid.
 */
public class InvalidContactMailtoLinkException extends RuntimeException {
    public InvalidContactMailtoLinkException() {
        super("Invalid email link");
    }
}
