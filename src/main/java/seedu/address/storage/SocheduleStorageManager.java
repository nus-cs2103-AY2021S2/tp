package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Sochedule data in local storage.
 */
public class SocheduleStorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private SocheduleStorage socheduleStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public SocheduleStorageManager(SocheduleStorage socheduleStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = null;
        this.userPrefsStorage = userPrefsStorage;
        this.socheduleStorage = socheduleStorage;
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
    // AddressBook placeholder functions will be removed once dependency
    // to AddressBookStorage is removed in Storage interface

    @Override
    public Path getAddressBookFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        // DO NOTHING
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        // DO NOTHING
    }

    // ================ Sochedule methods ==============================

    @Override
    public Path getSocheduleFilePath() {
        return socheduleStorage.getSocheduleFilePath();
    }

    @Override
    public Optional<ReadOnlySochedule> readSochedule() throws DataConversionException, IOException {
        return readSochedule(socheduleStorage.getSocheduleFilePath());
    }

    @Override
    public Optional<ReadOnlySochedule> readSochedule(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return socheduleStorage.readSochedule(filePath);
    }

    @Override
    public void saveSochedule(ReadOnlySochedule sochedule) throws IOException {
        saveSochedule(sochedule, getSocheduleFilePath());
    }

    @Override
    public void saveSochedule(ReadOnlySochedule sochedule, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        socheduleStorage.saveSochedule(sochedule, filePath);
    }
}
