package fooddiary.storage;

import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fooddiary.commons.core.GuiSettings;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFoodDiaryStorage foodDiaryStorage = new JsonFoodDiaryStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(foodDiaryStorage, userPrefsStorage);
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
    public void foodDiaryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFoodDiaryStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFoodDiaryStorageTest} class.
         */
        FoodDiary original = getTypicalFoodDiary();
        storageManager.saveFoodDiary(original);
        ReadOnlyFoodDiary retrieved = storageManager.readFoodDiary().get();
        assertEquals(original, new FoodDiary(retrieved));
    }

    @Test
    public void getFoodDiaryFilePath() {
        assertNotNull(storageManager.getFoodDiaryFilePath());
    }

}
