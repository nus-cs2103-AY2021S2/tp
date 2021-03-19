package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonResidenceTrackerStorage jsonResidenceTrackerStorage =
                new JsonResidenceTrackerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(jsonResidenceTrackerStorage, userPrefsStorage);
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
    public void residenceTrackerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonResidenceTrackerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonResidenceTrackerStorageTest} class.
         */
        ResidenceTracker original = getTypicalResidenceTracker();
        storageManager.saveResidenceTracker(original);
        ReadOnlyResidenceTracker retrieved = storageManager.readResidenceTracker().get();
        assertEquals(original, new ResidenceTracker(retrieved));
    }

    @Test
    public void getResidenceTrackerFilePath() {
        assertNotNull(storageManager.getResidenceTrackerFilePath());
    }

}
