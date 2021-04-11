package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.storage.addressbook.JsonAddressBookStorage;
import seedu.address.storage.connection.JsonConnectionStorage;
import seedu.address.storage.meetingbook.JsonMeetingBookStorage;
import seedu.address.storage.notebook.JsonNoteBookStorage;
import seedu.address.testutil.TypicalConnections;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonMeetingBookStorage meetingBookStorage = new JsonMeetingBookStorage(getTempFilePath("mb"));
        JsonNoteBookStorage noteBookStorage = new JsonNoteBookStorage(getTempFilePath("nb"));
        JsonConnectionStorage jsonConnectionStorage = new JsonConnectionStorage(getTempFilePath("connections"));
        storageManager = new StorageManager(addressBookStorage, meetingBookStorage,
                noteBookStorage, userPrefsStorage, jsonConnectionStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void allComponentsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         * Note: This is an integration test that verifies Storage Manager is properly wired to
         * {@link MeetingBookStorage} class
         * Note: This is an integration test that verifies Storage Manager is properly wired to
         * {@link PersonMeetingConnection} class.
         */

        AddressBook originalAddressBook = getTypicalAddressBook();
        storageManager.saveAddressBook(originalAddressBook);
        ReadOnlyAddressBook retrieved = storageManager.readAddressBook().get();
        assertEquals(originalAddressBook, new AddressBook(retrieved));

        MeetingBook originalMeetingBook = getTypicalMeetingBook();
        storageManager.saveMeetingBook(originalMeetingBook);
        ReadOnlyMeetingBook retrievedMeetingBook = storageManager.readMeetingBook().get();
        assertEquals(originalMeetingBook, new MeetingBook(retrievedMeetingBook));

        PersonMeetingConnection typicalPersonMeetingConnection =
                TypicalConnections.getTypicalPersonMeetingConnection();
        storageManager.saveConnection(typicalPersonMeetingConnection);
        PersonMeetingConnection retrievedConnection =
                storageManager.readConnection(originalMeetingBook, originalAddressBook).get();
        assertEquals(retrievedConnection, typicalPersonMeetingConnection);
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void getMeetingBookFilePath() {
        assertNotNull(storageManager.getMeetingBookFilePath());
    }

    @Test
    public void getConnectionsFilePath() {
        assertNotNull(storageManager.getConnectionFilePath());
    }
}
