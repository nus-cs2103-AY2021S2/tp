package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TutorsPet data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private DatesBookStorage datesBookStorage;
    private LessonBookStorage lessonBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code UserPrefStorage},
     * {@code DatesBookStorage} and {@code LessonBookStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          DatesBookStorage datesBookStorage, LessonBookStorage lessonBookStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.datesBookStorage = datesBookStorage;
        this.lessonBookStorage = lessonBookStorage;
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
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ DatesBook methods ==============================

    @Override
    public Path getDatesBookFilePath() {
        return datesBookStorage.getDatesBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDatesBook> readDatesBook() throws DataConversionException, IOException {
        return readDatesBook(datesBookStorage.getDatesBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDatesBook> readDatesBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return datesBookStorage.readDatesBook(filePath);
    }

    @Override
    public void saveDatesBook(ReadOnlyDatesBook datesBook) throws IOException {
        saveDatesBook(datesBook, datesBookStorage.getDatesBookFilePath());
    }

    @Override
    public void saveDatesBook(ReadOnlyDatesBook datesBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        datesBookStorage.saveDatesBook(datesBook, filePath);
    }

    // ================ LessonBook methods ==============================

    @Override
    public Path getLessonBookFilePath() {
        return lessonBookStorage.getLessonBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException {
        return readLessonBook(lessonBookStorage.getLessonBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read lesson data from file: " + filePath);
        return lessonBookStorage.readLessonBook(filePath);
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException {
        saveLessonBook(lessonBook, lessonBookStorage.getLessonBookFilePath());
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
        logger.fine("Attempting to write lesson data to data file: " + filePath);
        lessonBookStorage.saveLessonBook(lessonBook, filePath);
    }

}
