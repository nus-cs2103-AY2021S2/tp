package seedu.address.model.task.exceptions;

public class InvalidTaskComparatorVariableException extends RuntimeException {
    public InvalidTaskComparatorVariableException() {
        super("Invalid Comparator Input detected");
    }
}
