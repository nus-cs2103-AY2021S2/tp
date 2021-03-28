package seedu.address.model.connection.exceptions;

public class DuplicateConnectionException extends RuntimeException {
    public DuplicateConnectionException() {
        super("The connection between the meeting and the person has already existed.");
    }
}

