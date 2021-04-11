package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUniqueAliasMap;

/**
 * Represents a storage for {@link seedu.address.model.UniqueAliasMap}.
 */
public interface AliasMapStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAliasMapFilePath();

    /**
     * Returns UniqueAliasMap data as a {@link ReadOnlyUniqueAliasMap}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUniqueAliasMap> readAliasMap() throws DataConversionException, IOException;

    /**
     * @see #getAliasMapFilePath()
     */
    Optional<ReadOnlyUniqueAliasMap> readAliasMap(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUniqueAliasMap} to the storage.
     *
     * @param aliasMap cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAliasMap(ReadOnlyUniqueAliasMap aliasMap) throws IOException;

    /**
     * @see #saveAliasMap(ReadOnlyUniqueAliasMap)
     */
    void saveAliasMap(ReadOnlyUniqueAliasMap aliasMap, Path filePath) throws IOException;

}
