package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.UniqueFlashcardList;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the FlashBack level
 * Duplicates are not allowed (by .isSameCard comparison)
 */
public class VersionedFlashBack extends FlashBack {

    private final List<ReadOnlyFlashBack> flashBackStates;
    private int currentStatePointer;

    public VersionedFlashBack(ReadOnlyFlashBack toBeCopied) {
        super(toBeCopied);

        flashBackStates = new ArrayList<>();
        flashBackStates.add(new FlashBack(toBeCopied));
        currentStatePointer = 0;
    }

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
}
