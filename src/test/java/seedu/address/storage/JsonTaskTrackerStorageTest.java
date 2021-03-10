package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.HOON;
import static seedu.address.testutil.TypicalTasks.IDA;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskTracker;
import seedu.address.model.TaskTracker;

public class JsonTaskTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskTracker(null));
    }

    private java.util.Optional<ReadOnlyTaskTracker> readTaskTracker(String filePath) throws Exception {
        return new JsonTaskTrackerStorage(Paths.get(filePath)).readTaskTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskTracker("notJsonFormatTaskTracker.json"));
    }

    @Test
    public void readTaskTracker_invalidPersonTaskTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskTracker("invalidPersonTaskTracker.json"));
    }

    @Test
    public void readTaskTracker_invalidAndValidPersonTaskTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskTracker("invalidAndValidPersonTaskTracker.json"));
    }

    @Test
    public void readAndSaveTaskTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskTracker.json");
        TaskTracker original = getTypicalTaskTracker();
        JsonTaskTrackerStorage jsonTaskTrackerStorage = new JsonTaskTrackerStorage(filePath);

        // Save in new file and read back
        jsonTaskTrackerStorage.saveTaskTracker(original, filePath);
        ReadOnlyTaskTracker readBack = jsonTaskTrackerStorage.readTaskTracker(filePath).get();
        assertEquals(original, new TaskTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonTaskTrackerStorage.saveTaskTracker(original, filePath);
        readBack = jsonTaskTrackerStorage.readTaskTracker(filePath).get();
        assertEquals(original, new TaskTracker(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonTaskTrackerStorage.saveTaskTracker(original); // file path not specified
        readBack = jsonTaskTrackerStorage.readTaskTracker().get(); // file path not specified
        assertEquals(original, new TaskTracker(readBack));

    }

    @Test
    public void saveTaskTracker_nullTaskTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskTracker} at the specified {@code filePath}.
     */
    private void saveTaskTracker(ReadOnlyTaskTracker taskTracker, String filePath) {
        try {
            new JsonTaskTrackerStorage(Paths.get(filePath))
                    .saveTaskTracker(taskTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskTracker(new TaskTracker(), null));
    }
}
