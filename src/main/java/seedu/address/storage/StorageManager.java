package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<Pair<ReadOnlyAddressBook, AppointmentBook>> readBooks() throws DataConversionException, IOException {
        return readBooks(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Pair<Optional<ReadOnlyAddressBook>, Optional<AppointmentBook>> readAllBooks() throws DataConversionException, IOException {
        return new Pair(this.readBooks(), readAppointmentBook());
    }

    private Optional<AppointmentBook> readAppointmentBook() {
        return null;
    }

    @Override
    public Optional<Pair<ReadOnlyAddressBook, AppointmentBook>> readBooks(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readBooks(filePath);
    }

    @Override
    public void saveBooks(ReadOnlyAddressBook addressBook, AppointmentBook appointmentBook) throws IOException {
        saveBooks(addressBook, appointmentBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveBooks(ReadOnlyAddressBook addressBook, AppointmentBook appointmentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveBooks(addressBook, appointmentBook, filePath);
    }

}
