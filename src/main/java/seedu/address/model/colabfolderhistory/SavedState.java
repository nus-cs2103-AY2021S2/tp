package seedu.address.model.colabfolderhistory;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(colabFolder);
        this.colabFolder = colabFolder;
        this.commandResult = commandResult;
    }

    public ReadOnlyColabFolder getColabFolder() {
        return colabFolder;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    /**
     * Checks if {@code commandResult} is null.
     *
     * @return true if {@code commandResult} is null, false otherwise.
     */
    public boolean hasNoCommandResult() {
        return commandResult == null;
    }
}
