package seedu.address.model;

import java.util.ArrayList;
import java.util.List;


/*
@@author marc-97-reused
Reused from
https://github.com/se-edu/addressbook-level4/blob/master/src/main/java/seedu/address/model/VersionedAddressBook.java
with minor modification
 */
/**
 * {@code FlashBack} with states tracking for undo
 */
public class VersionedFlashBack extends FlashBack {

    private final List<ReadOnlyFlashBack> flashBackStates;
    private int currentStatePointer;

    /**
     * Creates a VersionedFlashBack using the cards in the {@code toBeCopied}
     */
    public VersionedFlashBack(ReadOnlyFlashBack toBeCopied) {
        super(toBeCopied);

        flashBackStates = new ArrayList<>();
        flashBackStates.add(new FlashBack(toBeCopied));
        currentStatePointer = 0;
    }

    /**
     * Saves updated state of FlashBack into the list of states
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        flashBackStates.add(new FlashBack(this));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        flashBackStates.subList(currentStatePointer + 1, flashBackStates.size()).clear();
    }

    /**
     * Restores FlashBack to its previous state
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(flashBackStates.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has flashback states to undo
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has flashback states to redo
     */
    public boolean canRedo() {
        return currentStatePointer < flashBackStates.size() - 1;
    }

    /**
     * Restores FlashBack to its state before the undo command
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(flashBackStates.get(currentStatePointer));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedFlashBack // instanceof handles nulls
                && flashBackStates.equals(((VersionedFlashBack) other).flashBackStates)
                && currentStatePointer == ((VersionedFlashBack) other).currentStatePointer
                && super.equals((VersionedFlashBack) other));
    }

    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of flashBackStates list, unable to undo");
        }
    }

    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of flashBackStates list, unable to redo");
        }
    }
}
//@@author
