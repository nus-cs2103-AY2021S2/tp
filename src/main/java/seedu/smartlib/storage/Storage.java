package seedu.smartlib.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.smartlib.commons.exceptions.DataConversionException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends SmartLibStorage, UserPrefsStorage {

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.smartlib.model.ReadOnlyUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /**
     * Returns the file path of the data file.
     */
    @Override
    Path getSmartLibFilePath();

    /**
     * Returns SmartLib data as a {@link ReadOnlySmartLib}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    Optional<ReadOnlySmartLib> readSmartLib() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySmartLib} to the storage.
     * @param smartLib cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveSmartLib(ReadOnlySmartLib smartLib) throws IOException;

}
