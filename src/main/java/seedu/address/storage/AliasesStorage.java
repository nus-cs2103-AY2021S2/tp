package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUniqueAliasMap;

/**
 * Represents a storage for {@link seedu.address.model.UniqueAliasMap}.
 */
public interface AliasesStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAliasesFilePath();

    /**
     * Returns UniqueAliasMap data as a {@link ReadOnlyUniqueAliasMap}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUniqueAliasMap> readAliases() throws DataConversionException, IOException;

    /**
     * @see #getAliasesFilePath()
     */
    Optional<ReadOnlyUniqueAliasMap> readAliases(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUniqueAliasMap} to the storage.
     * @param aliases cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAliases(ReadOnlyUniqueAliasMap aliases) throws IOException;

    /**
     * @see #saveAliases(ReadOnlyUniqueAliasMap)
     */
    void saveAliases(ReadOnlyUniqueAliasMap aliases, Path filePath) throws IOException;

}
