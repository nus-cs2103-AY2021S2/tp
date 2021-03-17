package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CakeCollate;
import seedu.address.model.ReadOnlyCakeCollate;

/**
 * Represents a storage for {@link CakeCollate}.
 */
public interface CakeCollateStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCakeCollateFilePath();

    /**
     * Returns CakeCollate data as a {@link ReadOnlyCakeCollate}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCakeCollate> readCakeCollate() throws DataConversionException, IOException;

    /**
     * @see #getCakeCollateFilePath()
     */
    Optional<ReadOnlyCakeCollate> readCakeCollate(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCakeCollate} to the storage.
     * @param cakeCollate cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCakeCollate(ReadOnlyCakeCollate cakeCollate) throws IOException;

    /**
     * @see #saveCakeCollate(ReadOnlyCakeCollate)
     */
    void saveCakeCollate(ReadOnlyCakeCollate cakeCollate, Path filePath) throws IOException;

}
