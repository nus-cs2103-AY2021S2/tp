package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.storage.addressbook.AddressBookStorage;
import seedu.address.storage.connection.ConnectionStorage;
import seedu.address.storage.meetingbook.MeetingBookStorage;
import seedu.address.storage.notebook.NoteBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage,
        UserPrefsStorage, MeetingBookStorage, NoteBookStorage, ConnectionStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;


    // ==========================Meeting Book ============================================================

    @Override
    Path getMeetingBookFilePath();

    @Override
    Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException;

    @Override
    void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException;

    // ================ NoteBook methods =====================================================
    @Override
    Path getNoteBookFilePath();

    @Override
    Optional<ReadOnlyNoteBook> readNoteBook() throws DataConversionException, IOException;

    @Override
    void saveNoteBook(ReadOnlyNoteBook noteBook) throws IOException;

    //============PersonConnection methods ============================================================
    @Override
    Path getConnectionFilePath();

    @Override
    public Optional<PersonMeetingConnection> readConnection(ReadOnlyMeetingBook meetingBook,
                                                            ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;
    @Override
    public void saveConnection(PersonMeetingConnection connection) throws IOException;



}
