package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyDietLah;

/**
 * Represents a storage for {@link seedu.address.model.DietLah}.
 */
public interface DietLahStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDietLahFilePath();

    /**
     * Returns DietLah data as a {@link ReadOnlyDietLah}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDietLah> readDietLah() throws DataConversionException, IOException;

    /**
     * @see #getDietLahFilePath()
     */
    Optional<ReadOnlyDietLah> readDietLah(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDietLah} to the storage.
     * @param dietLah cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDietLah(ReadOnlyDietLah dietLah) throws IOException;

    /**
     * @see #saveDietLah(ReadOnlyDietLah)
     */
    void saveDietLah(ReadOnlyDietLah dietLah, Path filePath) throws IOException;

}
