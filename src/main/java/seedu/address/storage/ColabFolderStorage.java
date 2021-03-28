package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ColabFolder;
import seedu.address.model.ReadOnlyColabFolder;

/**
 * Represents a storage for {@link ColabFolder}.
 */
public interface ColabFolderStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getColabFolderFilePath();

    /**
     * Returns ColabFolder data as a {@link ReadOnlyColabFolder}.
     *
     * @return A {@link ReadOnlyColabFolder} or {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyColabFolder> readColabFolder() throws DataConversionException, IOException;

    /**
     * @see #getColabFolderFilePath()
     */
    Optional<ReadOnlyColabFolder> readColabFolder(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyColabFolder} to the storage.
     *
     * @param colabFolder cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveColabFolder(ReadOnlyColabFolder colabFolder) throws IOException;

    /**
     * @see #saveColabFolder(ReadOnlyColabFolder)
     */
    void saveColabFolder(ReadOnlyColabFolder colabFolder, Path filePath) throws IOException;

}
