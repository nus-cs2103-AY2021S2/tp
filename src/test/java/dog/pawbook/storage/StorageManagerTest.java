package dog.pawbook.storage;

import static dog.pawbook.testutil.TypicalOwners.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.model.Database;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonDatabaseStorage databaseStorage = new JsonDatabaseStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(databaseStorage, userPrefsStorage);
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
    public void databaseReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDatabaseStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonDatabaseStorageTest} class.
         */
        Database original = getTypicalDatabase();
        storageManager.saveDatabase(original);
        ReadOnlyDatabase retrieved = storageManager.readDatabase().get();
        assertEquals(original, new Database(retrieved));
    }

    @Test
    public void getDatabaseFilePath() {
        assertNotNull(storageManager.getDatabaseFilePath());
    }

}
