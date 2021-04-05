package seedu.heymatez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.heymatez.commons.core.GuiSettings;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonHeyMatezStorage heyMatezStorage = new JsonHeyMatezStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(heyMatezStorage, userPrefsStorage);
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
    public void heyMatezReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonHeyMatezStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonHeyMatezStorageTest} class.
         */
        HeyMatez original = getTypicalHeyMatez();
        storageManager.saveHeyMatez(original);
        ReadOnlyHeyMatez retrieved = storageManager.readHeyMatez().get();
        assertEquals(original, new HeyMatez(retrieved));
    }

    @Test
    public void getHeyMatezFilePath() {
        assertNotNull(storageManager.getHeyMatezFilePath());
    }

}
