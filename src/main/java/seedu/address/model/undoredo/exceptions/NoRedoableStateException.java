// Solution below adapted from AB-4: https://github.com/se-edu/addressbook-level4
package seedu.address.model.undoredo.exceptions;

/**
 * Thrown when there are no more future states to redo.
 */
public class NoRedoableStateException extends RedoException {
    /**
     * Constructs a {@code NoRedoableStateException} with a default message.
     */
    public NoRedoableStateException() {
        super("Cannot redo: no future states remaining!");
    }
}
