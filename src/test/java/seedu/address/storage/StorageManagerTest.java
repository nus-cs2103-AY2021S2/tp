package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyTeachingAssistant;
import seedu.address.model.TeachingAssistant;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTeachingAssistantStorage teachingAssistantStorage = new JsonTeachingAssistantStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(teachingAssistantStorage, userPrefsStorage);
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
    public void teachingAssistantReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTeachingAssistantStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTeachingAssistantStorageTest} class.
         */
        TeachingAssistant original = getTypicalTeachingAssistant();
        storageManager.saveTeachingAssistant(original);
        ReadOnlyTeachingAssistant retrieved = storageManager.readTeachingAssistant().get();
        assertEquals(original, new TeachingAssistant(retrieved));
    }

    @Test
    public void getTeachingAssistantFilePath() {
        assertNotNull(storageManager.getTeachingAssistantFilePath());
    }

}
