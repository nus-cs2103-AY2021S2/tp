// Solution below adapted from AB-4: https://github.com/se-edu/addressbook-level4
package seedu.address.model.undoredo;

import java.util.LinkedList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.undoredo.exceptions.NoRedoableStateException;
import seedu.address.model.undoredo.exceptions.NoUndoableStateException;
import seedu.address.model.undoredo.exceptions.RedoException;
import seedu.address.model.undoredo.exceptions.UndoException;

/**
 * An {@code AddressBook} that maintains a state for undo/redo operations.
 */
public class StatefulAddressBook extends AddressBook {
    private final List<ReadOnlyAddressBook> stateHistory;
    private int currStatePtr;

    /**
     * Constructs a {@code StatefulAddressBook} with the given initial state.
     *
     * @param initialState The initial state.
     */
    public StatefulAddressBook(ReadOnlyAddressBook initialState) {
        super(initialState);
        stateHistory = new LinkedList<>();
        stateHistory.add(new AddressBook(this));
        currStatePtr = 0;
    }

    /**
     * Returns true if a redo operation is possible. False otherwise.
     *
     * @return True if a redo operation is possible. False otherwise.
     */
    public boolean canRedo() {
        return hasRemainingRedoableStates();
    }

    /**
     * Returns true if an undo operation is possible. False otherwise.
     *
     * @return True if an undo operation is possible. False otherwise.
     */
    public boolean canUndo() {
        return hasRemainingUndoableStates();
    }

    /**
     * Saves the current state of the address book for undo/redo operations.
     */
    public void commit() {
        removeStatesAfterCurrent();
        stateHistory.add(new AddressBook(this));
        currStatePtr++;
    }

    /**
     * Sets the current state of the address book to the next state, if any.
     *
     * @throws RedoException If the redo operation fails for any reason.
     */
    public void redo() throws RedoException {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currStatePtr++;
        resetData(stateHistory.get(currStatePtr));
    }

    /**
     * Reverts the current state of the address book to the previous state, if any.
     *
     * @throws UndoException If the undo operation fails for any reason.
     */
    public void undo() throws UndoException {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currStatePtr--;
        resetData(stateHistory.get(currStatePtr));
    }

    private boolean hasRemainingRedoableStates() {
        return currStatePtr < stateHistory.size() - 1;
    }

    private boolean hasRemainingUndoableStates() {
        return currStatePtr > 0;
    }

    /**
     * Removes all states at indices higher than the current state pointer, if any.
     */
    private void removeStatesAfterCurrent() {
        stateHistory.subList(currStatePtr + 1, stateHistory.size()).clear();
    }
}
