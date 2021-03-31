package seedu.address.model.colabfolderhistory.exceptions;

/**
 * Thrown when trying to {@code undo()} but no undoable state found.
 */
public class NoUndoableStateException extends RuntimeException {
    public NoUndoableStateException() {
        super("Nothing to undo.");
    }
}
