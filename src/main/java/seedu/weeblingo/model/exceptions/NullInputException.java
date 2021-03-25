package seedu.weeblingo.model.exceptions;

/**
 * A sub-class of RuntimeException that represents exceptions thrown because of null input values.
 * This Exception will not be thrown if the developer's code logic is correct. During normal application
 * executions, this Exception will never be thrown.
 */
public class NullInputException extends RuntimeException {
    public NullInputException() {
        super("The input must not be null. The application will exit");
    }

    public NullInputException(String message) {
        super(message);
    }
}
