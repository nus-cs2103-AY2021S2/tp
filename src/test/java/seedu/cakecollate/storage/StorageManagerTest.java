package seedu.cakecollate.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCakeCollateStorage cakeCollateStorage = new JsonCakeCollateStorage(getTempFilePath("ab"));
        JsonOrderItemsStorage orderItemsStorage = new JsonOrderItemsStorage(getTempFilePath("item"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));

        storageManager = new StorageManager(cakeCollateStorage, userPrefsStorage, orderItemsStorage);
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
    public void cakeCollateReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCakeCollateStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCakeCollateStorageTest} class.
         */
        CakeCollate original = getTypicalCakeCollate();
        storageManager.saveCakeCollate(original);
        ReadOnlyCakeCollate retrieved = storageManager.readCakeCollate().get();
        assertEquals(original, new CakeCollate(retrieved));
    }

    @Test
    public void getCakeCollateFilePath() {
        assertNotNull(storageManager.getCakeCollateFilePath());
    }

}
