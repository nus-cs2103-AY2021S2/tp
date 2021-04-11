package seedu.address.model.connection.exceptions;

/**
 * Signals that the operation will result in the connections between meetings and persons doesn't exist.
 */
public class ConnectionNoFoundException extends RuntimeException {
    public ConnectionNoFoundException() {
        super("The linkage between the meeting and the person does not exist.");
    }
}
