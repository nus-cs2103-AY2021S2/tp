package seedu.storemando.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;

/**
 * Represents a storage for {@link StoreMando}.
 */
public interface StoreMandoStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStoreMandoFilePath();

    /**
     * Returns StoreMando data as a {@link ReadOnlyStoreMando}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStoreMando> readStoreMando() throws DataConversionException, IOException;

    /**
     * @see #getStoreMandoFilePath()
     */
    Optional<ReadOnlyStoreMando> readStoreMando(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStoreMando} to the storage.
     *
     * @param storeMando cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStoreMando(ReadOnlyStoreMando storeMando) throws IOException;

    /**
     * @see #saveStoreMando(ReadOnlyStoreMando)
     */
    void saveStoreMando(ReadOnlyStoreMando storeMando, Path filePath) throws IOException;

}
