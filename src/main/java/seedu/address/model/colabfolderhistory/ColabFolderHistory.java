package seedu.address.model.colabfolderhistory;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.colabfolderhistory.exceptions.NoRedoableStateException;
import seedu.address.model.colabfolderhistory.exceptions.NoUndoableStateException;

/**
 * Stores a history of {@code ColabFolder}.
 */
public class ColabFolderHistory {
    private final List<SavedState> savedStateList;
    private int currentStatePointer;

    /**
     * Creates a {@code ColabFolderHistory} object with the initial state of the {@code ColabFolder}.
     *
     * @param initialColabFolder The initial state of the ColabFolder.
     */
    public ColabFolderHistory(ReadOnlyColabFolder initialColabFolder) {
        savedStateList = new ArrayList<>();
        savedStateList.add(new SavedState(initialColabFolder));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code ColabFolder} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit(ReadOnlyColabFolder colabFolder, CommandResult commandResult) {
        removeStatesAfterCurrentPointer();
        savedStateList.add(new SavedState(colabFolder, commandResult));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        savedStateList.subList(currentStatePointer + 1, savedStateList.size()).clear();
    }

    /**
     * Returns the previous state of the {@code ColabFolder}.
     */
    public SavedState undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        return savedStateList.get(currentStatePointer);
    }

    /**
     * Returns the next state of the {@code ColabFolder}.
     */
    public SavedState redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        return savedStateList.get(currentStatePointer);
    }

    /**
     * Returns true if {@code undo()} has colab folder states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has colab folder states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < savedStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ColabFolderHistory)) {
            return false;
        }

        ColabFolderHistory otherColabFolderHistory = (ColabFolderHistory) other;

        // state check
        return savedStateList.equals(otherColabFolderHistory.savedStateList)
                && currentStatePointer == otherColabFolderHistory.currentStatePointer;
    }

}
