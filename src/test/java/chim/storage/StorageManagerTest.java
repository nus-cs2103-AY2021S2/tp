package chim.storage;

import static chim.testutil.TypicalModels.getTypicalChim;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import chim.commons.core.GuiSettings;
import chim.commons.core.GuiSettings.PanelToShow;
import chim.model.Chim;
import chim.model.ReadOnlyChim;
import chim.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonChimStorage chimStorage = new JsonChimStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(chimStorage, userPrefsStorage);
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
        original.setGuiSettings(new GuiSettings(PanelToShow.CUSTOMER_LIST, 300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void chimReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonChimStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonChimStorageTest} class.
         */
        Chim original = getTypicalChim();
        storageManager.saveChim(original);
        ReadOnlyChim retrieved = storageManager.readChim().get();
        assertEquals(original, new Chim(retrieved));
    }

    @Test
    public void getChimFilePath() {
        assertNotNull(storageManager.getChimFilePath());
    }

}
