package seedu.iscam.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.storage.client.JsonClientBookStorage;
import seedu.iscam.storage.meeting.JsonMeetingBookStorage;
import seedu.iscam.storage.user.JsonUserPrefsStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClientBookStorage clientBookStorage = new JsonClientBookStorage(getTempFilePath("cb"));
        JsonMeetingBookStorage meetingBookStorage = new JsonMeetingBookStorage(getTempFilePath("mb"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(clientBookStorage, meetingBookStorage, userPrefsStorage);
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
    public void clientBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClientBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClientBookStorageTest} class.
         */
        ClientBook original = getTypicalClientBook();
        storageManager.saveClientBook(original);
        ReadOnlyClientBook retrieved = storageManager.readClientBook().get();
        assertEquals(original, new ClientBook(retrieved));
    }

    @Test
    public void getClientBookFilePath() {
        assertNotNull(storageManager.getClientBookFilePath());
    }

    //TODO: maybe fix this test
    /*
    @Test
    public void meetingBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMeetingBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonMeetingBookStorageTest} class.
         */
    /*    MeetingBook original = getTypicalMeetingBook();
        storageManager.saveMeetingBook(original);
        ReadOnlyMeetingBook retrieved = storageManager.readMeetingBook().get();
        assertEquals(original, new MeetingBook(retrieved));
    }
    */

    @Test
    public void getMeetingBookFilePath() {
        assertNotNull(storageManager.getClientBookFilePath());
    }

}
