package seedu.address.model.person.exceptions;

public class TimeClashException extends Exception {
    public TimeClashException() {
    }

    public TimeClashException(String message) {
        super(message);
    }
}
