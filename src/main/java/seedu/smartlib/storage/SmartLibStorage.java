package seedu.smartlib.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.smartlib.commons.exceptions.DataConversionException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;

/**
 * Represents a storage for {@link SmartLib}.
 */
public interface SmartLibStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSmartLibFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlySmartLib}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySmartLib> readSmartLib() throws DataConversionException, IOException;

    /**
     * @see #getSmartLibFilePath()
     */
    Optional<ReadOnlySmartLib> readSmartLib(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySmartLib} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSmartLib(ReadOnlySmartLib addressBook) throws IOException;

    /**
     * @see #saveSmartLib(ReadOnlySmartLib)
     */
    void saveSmartLib(ReadOnlySmartLib addressBook, Path filePath) throws IOException;

}
