package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AppointmentBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAppointmentBookStorage appointmentBookStorage = new JsonAppointmentBookStorage(
                getTempFilePath("abAppointment"));
        JsonPropertyBookStorage propertyBookStorage = new JsonPropertyBookStorage(
                getTempFilePath("abProperty"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(appointmentBookStorage, propertyBookStorage, userPrefsStorage);
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
    public void appointmentBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAppointmentBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAppointmentBookStorageTest} class.
         */
        AppointmentBook original = getTypicalAppointmentBook();
        storageManager.saveAppointmentBook(original);
        ReadOnlyAppointmentBook retrieved = storageManager.readAppointmentBook().get();
        assertEquals(original, new AppointmentBook(retrieved));
    }

    @Test
    public void getAppointmentBookFilePath() {
        assertNotNull(storageManager.getAppointmentBookFilePath());
    }

    @Test
    public void propertyBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPropertyBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPropertyBookStorageTest} class.
         */
        PropertyBook original = getTypicalPropertyBook();
        storageManager.savePropertyBook(original);
        ReadOnlyPropertyBook retrieved = storageManager.readPropertyBook().get();
        assertEquals(original, new PropertyBook(retrieved));
    }

    @Test
    public void getPropertyBookFilePath() {
        assertNotNull(storageManager.getPropertyBookFilePath());
    }

}
