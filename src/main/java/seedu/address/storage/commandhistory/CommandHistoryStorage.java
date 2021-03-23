package seedu.address.storage.commandhistory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.commandhistory.ReadOnlyCommandHistory;

/**
 * Represents a storage for {@link seedu.address.model.commandhistory.CommandHistory}.
 */
public interface CommandHistoryStorage {
    /**
     * Returns the file path of the CommandHistory data file.
     */
    Path getCommandHistoryFilePath();

    /**
     * Returns CommandHistory data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws IOException If there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCommandHistory> readCommandHistory() throws IOException;

    /**
     * Saves the given {@link seedu.address.model.commandhistory.ReadOnlyCommandHistory} to the storage.
     *
     * @param commandHistory Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException;
}
