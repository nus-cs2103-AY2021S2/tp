package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.storage.addressbook.AddressBookStorage;
import seedu.address.storage.connection.ConnectionStorage;
import seedu.address.storage.meetingbook.MeetingBookStorage;
import seedu.address.storage.notebook.NoteBookStorage;

/**
 * Manages storage of AddressBook and MeetingBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private MeetingBookStorage meetingBookStorage;
    private NoteBookStorage noteBookStorage;
    private ConnectionStorage connectionStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage},
     * {@code meetingBookStorage}, {@code noteBookStorage} and {@code UserPrefStorage}
     */
    public StorageManager(AddressBookStorage addressBookStorage, MeetingBookStorage meetingBookStorage,
                          NoteBookStorage noteBookStorage, UserPrefsStorage userPrefsStorage,
                          ConnectionStorage connectionStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.meetingBookStorage = meetingBookStorage;
        this.noteBookStorage = noteBookStorage;
        this.connectionStorage = connectionStorage;
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

    // ================ MeetingBook methods ======================================================

    @Override
    public Path getMeetingBookFilePath() {
        return meetingBookStorage.getMeetingBookFilePath();
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException {
        return readMeetingBook(meetingBookStorage.getMeetingBookFilePath());
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return meetingBookStorage.readMeetingBook(filePath);
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException {
        saveMeetingBook(meetingBook, meetingBookStorage.getMeetingBookFilePath());
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        meetingBookStorage.saveMeetingBook(meetingBook, filePath);
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

    //===================== Person-Meeting Connection ===========================================================
    @Override
    public Path getConnectionFilePath() {
        return connectionStorage.getConnectionFilePath();
    }

    @Override
    public Optional<PersonMeetingConnection> readConnection(ReadOnlyMeetingBook meetingBook,
                                                            ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException {
        return readConnection(connectionStorage.getConnectionFilePath(), meetingBook, addressBook);
    }

    @Override
    public Optional<PersonMeetingConnection> readConnection(Path filePath, ReadOnlyMeetingBook meetingBook,
                                                            ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return connectionStorage.readConnection(filePath, meetingBook, addressBook);
    }

    @Override
    public void saveConnection(PersonMeetingConnection personMeetingConnection) throws IOException {
        saveConnection(personMeetingConnection, connectionStorage.getConnectionFilePath());
    }
    @Override
    public void saveConnection(PersonMeetingConnection personMeetingConnection, Path filePath) throws IOException {
        logger.fine("Attempting to write data to file: " + filePath);
        connectionStorage.saveConnection(personMeetingConnection, filePath);
    }

}
