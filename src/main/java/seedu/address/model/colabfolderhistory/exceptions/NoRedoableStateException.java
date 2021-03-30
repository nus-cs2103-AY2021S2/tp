package seedu.address.model.colabfolderhistory.exceptions;

/**
 * Thrown when trying to {@code redo()} but no redoable state found.
 */
public class NoRedoableStateException extends RuntimeException {
    public NoRedoableStateException() {
        super("Nothing to redo.");
    }
}
