// Solution below adapted from AB-4: https://github.com/se-edu/addressbook-level4
package seedu.address.model.undoredo.exceptions;

/**
 * Thrown when there are no more prior states to undo.
 */
public class NoUndoableStateException extends UndoException {
    /**
     * Constructs a {@code NoUndoableStateException} with a default message.
     */
    public NoUndoableStateException() {
        super("Cannot undo: no prior states remaining!");
    }
}
