package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Chim;
import seedu.address.model.ReadOnlyChim;

/**
 * Represents a storage for {@link Chim}.
 */
public interface ChimStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getChimFilePath();

    /**
     * Returns CHIM data as a {@link ReadOnlyChim}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyChim> readChim() throws DataConversionException, IOException;

    /**
     * @see #getChimFilePath()
     */
    Optional<ReadOnlyChim> readChim(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyChim} to the storage.
     * @param chim cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveChim(ReadOnlyChim chim) throws IOException;

    /**
     * @see #saveChim(ReadOnlyChim)
     */
    void saveChim(ReadOnlyChim chim, Path filePath) throws IOException;

}
