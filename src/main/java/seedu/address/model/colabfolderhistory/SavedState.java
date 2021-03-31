package seedu.address.model.colabfolderhistory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.ReadOnlyColabFolder;

/**
 * Encapsulates information regarding the state of the {@code ColabFolder} at a certain point in time.
 */
public class SavedState {
    private final ReadOnlyColabFolder colabFolder;
    private final CommandResult commandResult;

    /**
     * Constructs a {@code SavedState} object with a {@code colabFolder} and {@code commandResult}.
     *
     * @param colabFolder The {@code ReadOnlyColabFolder} to store, should not be null.
     * @param commandResult The {@code CommandResult} to store.
     */
    public SavedState(ReadOnlyColabFolder colabFolder, CommandResult commandResult) {
        requireAllNonNull(colabFolder, commandResult);
        this.colabFolder = colabFolder;
        this.commandResult = commandResult;
    }

    /**
     * Constructs a {@code SavedState} object with a {@code colabFolder}. Sets {@code commandResult to null}.
     *
     * @param colabFolder The {@code ReadOnlyColabFolder} to store, should not be null.
     */
    public SavedState(ReadOnlyColabFolder colabFolder) {
        requireNonNull(colabFolder);
        this.colabFolder = colabFolder;
        this.commandResult = null;
    }

    public ReadOnlyColabFolder getColabFolder() {
        return colabFolder;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SavedState)) {
            return false;
        }

        SavedState otherSavedState = (SavedState) other;

        // state check
        return colabFolder.equals(otherSavedState.colabFolder)
                && commandResult == otherSavedState.commandResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colabFolder, commandResult);
    }
}
