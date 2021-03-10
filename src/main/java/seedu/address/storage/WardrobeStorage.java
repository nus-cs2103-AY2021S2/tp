package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyWardrobe;

/**
 * Represents a storage for {@link seedu.address.model.Wardrobe}.
 */
public interface WardrobeStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWardrobeFilePath();

    /**
     * Returns Wardrobe data as a {@link ReadOnlyWardrobe}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyWardrobe> readWardrobe() throws DataConversionException, IOException;

    /**
     * @see #getWardrobeFilePath()
     */
    Optional<ReadOnlyWardrobe> readWardrobe(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyWardrobe} to the storage.
     * @param wardrobe cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWardrobe(ReadOnlyWardrobe wardrobe) throws IOException;

    /**
     * @see #saveWardrobe(ReadOnlyWardrobe)
     */
    void saveWardrobe(ReadOnlyWardrobe wardrobe, Path filePath) throws IOException;

}
