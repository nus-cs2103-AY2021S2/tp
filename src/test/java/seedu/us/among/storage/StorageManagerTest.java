package seedu.us.among.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.testutil.TypicalEndpoints;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonEndpointListStorage endpointListStorage = new JsonEndpointListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(endpointListStorage, userPrefsStorage);
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
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6, "imposter"));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void endpointListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonEndpointListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonEndpointListStorageTest} class.
         */
        EndpointList original = TypicalEndpoints.getTypicalEndpointList();
        storageManager.saveEndpointList(original);
        ReadOnlyEndpointList retrieved = storageManager.readEndpointList().get();
        assertEquals(original, new EndpointList(retrieved));
    }

    @Test
    public void getEndpointListFilePath() {
        assertNotNull(storageManager.getEndpointListFilePath());
    }

}
