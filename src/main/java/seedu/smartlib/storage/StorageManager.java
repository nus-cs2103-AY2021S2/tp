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
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SmartLibStorage smartLibStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SmartLibStorage smartLibStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.smartLibStorage = smartLibStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return smartLibStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlySmartLib> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(smartLibStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlySmartLib> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return smartLibStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlySmartLib addressBook) throws IOException {
        saveAddressBook(addressBook, smartLibStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlySmartLib addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        smartLibStorage.saveAddressBook(addressBook, filePath);
    }

}
