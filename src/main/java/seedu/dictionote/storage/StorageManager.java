package seedu.dictionote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.UserPrefs;


/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ContactsListStorage contactsListStorage;
    private UserPrefsStorage userPrefsStorage;
    private NoteBookStorage noteBookStorage;
    private DictionaryStorage dictionaryStorage;
    private DefinitionBookStorage definitionBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ContactsListStorage contactsListStorage,
                          UserPrefsStorage userPrefsStorage,
                          NoteBookStorage noteBookStorage,
                          DictionaryStorage dictionaryStorage,
                          DefinitionBookStorage definitionBookStorage) {
        super();
        this.contactsListStorage = contactsListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.noteBookStorage = noteBookStorage;
        this.dictionaryStorage = dictionaryStorage;
        this.definitionBookStorage = definitionBookStorage;
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
    public Path getContactsListFilePath() {
        return contactsListStorage.getContactsListFilePath();
    }

    @Override
    public Optional<ReadOnlyContactsList> readContactsList() throws DataConversionException, IOException {
        return readContactsList(contactsListStorage.getContactsListFilePath());
    }

    @Override
    public Optional<ReadOnlyContactsList> readContactsList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactsListStorage.readContactsList(filePath);
    }

    @Override
    public void saveContactsList(ReadOnlyContactsList addressBook) throws IOException {
        saveContactsList(addressBook, contactsListStorage.getContactsListFilePath());
    }

    @Override
    public void saveContactsList(ReadOnlyContactsList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactsListStorage.saveContactsList(addressBook, filePath);
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

    // ================ DefinitionBook methods ==============================

    @Override
    public Path getDefinitionBookFilePath() {
        return definitionBookStorage.getDefinitionBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDefinitionBook> readDefinitionBook() throws DataConversionException, IOException {
        return readDefinitionBook(definitionBookStorage.getDefinitionBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDefinitionBook> readDefinitionBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return definitionBookStorage.readDefinitionBook(filePath);
    }

    @Override
    public void saveDefinitionBook(ReadOnlyDefinitionBook definitionBook) throws IOException {
        saveDefinitionBook(definitionBook, definitionBookStorage.getDefinitionBookFilePath());
    }

    @Override
    public void saveDefinitionBook(ReadOnlyDefinitionBook definitionBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        definitionBookStorage.saveDefinitionBook(definitionBook, filePath);
    }
}
