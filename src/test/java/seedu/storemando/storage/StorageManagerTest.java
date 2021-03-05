package seedu.storemando.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonStoreMandoStorage storeMandoStorage = new JsonStoreMandoStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(storeMandoStorage, userPrefsStorage);
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
    public void storeMandoReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStoreMandoStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStoreMandoStorageTest} class.
         */
        StoreMando original = getTypicalStoreMando();
        storageManager.saveStoreMando(original);
        ReadOnlyStoreMando retrieved = storageManager.readStoreMando().get();
        assertEquals(original, new StoreMando(retrieved));
    }

    @Test
    public void getStoreMandoFilePath() {
        assertNotNull(storageManager.getStoreMandoFilePath());
    }

}
