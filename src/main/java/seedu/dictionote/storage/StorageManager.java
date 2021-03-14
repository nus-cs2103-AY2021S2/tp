package seedu.dictionote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.ReadOnlyAddressBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.UserPrefs;


/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private NoteBookStorage noteBookStorage;
    private DictionaryStorage dictionaryStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          NoteBookStorage noteBookStorage,
                          DictionaryStorage dictionaryStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.noteBookStorage = noteBookStorage;
        this.dictionaryStorage = dictionaryStorage;
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

    // ================ NoteBook methods ==============================

    @Override
    public Path getNoteBookFilePath() {
        return noteBookStorage.getNoteBookFilePath();
    }

    @Override
    public Optional<ReadOnlyNoteBook> readNoteBook() throws DataConversionException, IOException {
        return readNoteBook(noteBookStorage.getNoteBookFilePath());
    }

    @Override
    public Optional<ReadOnlyNoteBook> readNoteBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return noteBookStorage.readNoteBook(filePath);
    }

    @Override
    public void saveNoteBook(ReadOnlyNoteBook noteBook) throws IOException {
        saveNoteBook(noteBook, noteBookStorage.getNoteBookFilePath());
    }

    @Override
    public void saveNoteBook(ReadOnlyNoteBook noteBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        noteBookStorage.saveNoteBook(noteBook, filePath);
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

    // ================ Dictionary methods ==============================

    @Override
    public Path getDictionaryFilePath() {
        return dictionaryStorage.getDictionaryFilePath();
    }

    @Override
    public Optional<ReadOnlyDictionary> readDictionary() throws DataConversionException, IOException {
        return readDictionary(dictionaryStorage.getDictionaryFilePath());
    }

    @Override
    public Optional<ReadOnlyDictionary> readDictionary(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return dictionaryStorage.readDictionary(filePath);
    }

    @Override
    public void saveDictionary(ReadOnlyDictionary dictionary) throws IOException {
        saveDictionary(dictionary, dictionaryStorage.getDictionaryFilePath());
    }

    @Override
    public void saveDictionary(ReadOnlyDictionary dictionary, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dictionaryStorage.saveDictionary(dictionary, filePath);
    }
}
