package seedu.weeblingo.model.exceptions;

/**
 * A sub-class of RuntimeException that represents exceptions thrown because of empty string input values.
 * This Exception will not be thrown if the developer's code logic is correct. During normal application
 * executions, this Exception will never be thrown.
 */
public class EmptyStringInputException extends RuntimeException {
    public EmptyStringInputException() {
        super("The input string must not be empty. The application will exit");
    }

    public EmptyStringInputException(String message) {
        super(message);
    }
}
