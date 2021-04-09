package seedu.taskify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taskify.testutil.Assert.assertThrows;
import static seedu.taskify.testutil.TypicalTasks.TASK_1;
import static seedu.taskify.testutil.TypicalTasks.TASK_8;
import static seedu.taskify.testutil.TypicalTasks.TASK_9;
import static seedu.taskify.testutil.TypicalTasks.getTypicalTaskify;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.taskify.commons.exceptions.DataConversionException;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.Taskify;

public class JsonTaskifyParserStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskifyParserStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskify_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskify(null));
    }

    private java.util.Optional<ReadOnlyTaskify> readTaskify(String filePath) throws Exception {
        return new JsonTaskifyStorage(Paths.get(filePath)).readTaskifyData(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                       ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                       : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskify("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskify("notJsonFormatTaskify.json"));
    }

    @Test
    public void readTaskify_invalidTaskTaskify_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskify("invalidTaskTaskify.json"));
    }

    @Test
    public void readTaskify_invalidAndValidTaskTaskify_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskify("invalidAndValidTaskTaskify.json"));
    }

    @Test
    public void readAndSaveTaskify_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskify.json");
        Taskify original = getTypicalTaskify();
        JsonTaskifyStorage jsonTaskifyStorage = new JsonTaskifyStorage(filePath);

        // Save in new file and read back
        jsonTaskifyStorage.saveTaskifyData(original, filePath);
        ReadOnlyTaskify readBack = jsonTaskifyStorage.readTaskifyData(filePath).get();
        assertEquals(original, new Taskify(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(TASK_8);
        original.removeTask(TASK_1);
        jsonTaskifyStorage.saveTaskifyData(original, filePath);
        readBack = jsonTaskifyStorage.readTaskifyData(filePath).get();
        assertEquals(original, new Taskify(readBack));

        // Save and read without specifying file path
        original.addTask(TASK_9);
        jsonTaskifyStorage.saveTaskifyData(original); // file path not specified
        readBack = jsonTaskifyStorage.readTaskifyData().get(); // file path not specified
        assertEquals(original, new Taskify(readBack));

    }

    @Test
    public void saveTaskify_nullTaskify_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskify(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskify} at the specified {@code filePath}.
     */
    private void saveTaskify(ReadOnlyTaskify taskify, String filePath) {
        try {
            new JsonTaskifyStorage(Paths.get(filePath))
                    .saveTaskifyData(taskify, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskify_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskify(new Taskify(), null));
    }
}
