package seedu.address.model.project.exceptions;

/**
 * Signals that the operation will result in duplicate Todos.
 */
public class DuplicateTodoException extends RuntimeException {
    public DuplicateTodoException() {
        super("Operation would result in duplicate todos.");
    }
}
