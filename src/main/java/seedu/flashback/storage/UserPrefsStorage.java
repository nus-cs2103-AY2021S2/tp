package seedu.flashback.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashback.commons.exceptions.DataConversionException;
import seedu.flashback.model.ReadOnlyUserPrefs;
import seedu.flashback.model.UserPrefs;

/**
 * Represents a storage for {@link seedu.flashback.model.UserPrefs}.
 */
public interface UserPrefsStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.flashback.model.ReadOnlyUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

}
