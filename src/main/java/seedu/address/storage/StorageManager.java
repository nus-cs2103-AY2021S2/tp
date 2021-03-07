package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ModuleBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ModuleBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ModuleBook methods ==============================

    @Override
    public Path getModuleBookFilePath() {
        return addressBookStorage.getModuleBookFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleBook> readModuleBook() throws DataConversionException, IOException {
        return readModuleBook(addressBookStorage.getModuleBookFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleBook> readModuleBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readModuleBook(filePath);
    }

    @Override
    public void saveModuleBook(ReadOnlyModuleBook addressBook) throws IOException {
        saveModuleBook(addressBook, addressBookStorage.getModuleBookFilePath());
    }

    @Override
    public void saveModuleBook(ReadOnlyModuleBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveModuleBook(addressBook, filePath);
    }

}
