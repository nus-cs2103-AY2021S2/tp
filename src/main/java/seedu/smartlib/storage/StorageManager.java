package seedu.smartlib.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.smartlib.commons.core.LogsCenter;
import seedu.smartlib.commons.exceptions.DataConversionException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.UserPrefs;

/**
 * Manages storage of SmartLib data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SmartLibStorage smartLibStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SmartLibStorage} and {@code UserPrefStorage}.
     *
     * @param smartLibStorage storage for SmartLib.
     * @param userPrefsStorage storage for UserPrefs.
     */
    public StorageManager(SmartLibStorage smartLibStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.smartLibStorage = smartLibStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    /**
     * Returns the file path of the UserPrefs data file.
     *
     * @return the file path of the UserPrefs data file.
     */
    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @return userPrefs data from storage.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    /**
     * Saves the given {@link seedu.smartlib.model.ReadOnlyUserPrefs} to the storage.
     *
     * @param userPrefs cannot be null.
     * @throws IOException
     */
    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ SmartLib methods ==============================

    /**
     * Returns the file path of the data file.
     *
     * @return the file path of the data file.
     */
    @Override
    public Path getSmartLibFilePath() {
        return smartLibStorage.getSmartLibFilePath();
    }

    /**
     * Returns SmartLib data as a {@link ReadOnlySmartLib}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @return SmartLib's data.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlySmartLib> readSmartLib() throws DataConversionException, IOException {
        return readSmartLib(smartLibStorage.getSmartLibFilePath());
    }

    /**
     * @see #getSmartLibFilePath()
     *
     * @param filePath file path leading to the SmartLib data.
     * @return SmartLib's data.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlySmartLib> readSmartLib(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return smartLibStorage.readSmartLib(filePath);
    }

    /**
     * Saves the given {@link ReadOnlySmartLib} to the storage.
     *
     * @param smartLib cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveSmartLib(ReadOnlySmartLib smartLib) throws IOException {
        saveSmartLib(smartLib, smartLibStorage.getSmartLibFilePath());
    }

    /**
     * @see #saveSmartLib(ReadOnlySmartLib)
     *
     * @param smartLib cannot be null.
     * @param filePath file path leading to SmartLib's data.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveSmartLib(ReadOnlySmartLib smartLib, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        smartLibStorage.saveSmartLib(smartLib, filePath);
    }

}
