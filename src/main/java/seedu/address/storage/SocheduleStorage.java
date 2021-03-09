package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySochedule;

/**
 * Represents a storage for {@link seedu.address.model.Sochedule}.
 */
public interface SocheduleStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSocheduleFilePath();

    /**
     * Returns Sochedule data as a {@link ReadOnlySochedule}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySochedule> readSochedule() throws DataConversionException, IOException;

    /**
     * @see #getSocheduleFilePath()
     */
    Optional<ReadOnlySochedule> readSochedule(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySochedule} to the storage.
     * @param sochedule cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSochedule(ReadOnlySochedule sochedule) throws IOException;

    /**
     * @see #saveSochedule(ReadOnlySochedule)
     */
    void saveSochedule(ReadOnlySochedule sochedule, Path filePath) throws IOException;

}
