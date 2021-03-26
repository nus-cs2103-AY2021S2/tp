package seedu.taskify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taskify.testutil.Assert.assertThrows;
import static seedu.taskify.testutil.TypicalTasks.TASK_1;
import static seedu.taskify.testutil.TypicalTasks.TASK_8;
import static seedu.taskify.testutil.TypicalTasks.TASK_9;
import static seedu.taskify.testutil.TypicalTasks.getTypicalAddressBook;

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
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyTaskify> readAddressBook(String filePath) throws Exception {
        return new JsonTaskifyStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                       ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                       : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatTaskify.json"));
    }

    @Test
    public void readAddressBook_invalidTaskAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidTaskTaskify.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidTaskAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidTaskTaskify.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Taskify original = getTypicalAddressBook();
        JsonTaskifyStorage jsonTaskifyStorage = new JsonTaskifyStorage(filePath);

        // Save in new file and read back
        jsonTaskifyStorage.saveAddressBook(original, filePath);
        ReadOnlyTaskify readBack = jsonTaskifyStorage.readAddressBook(filePath).get();
        assertEquals(original, new Taskify(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(TASK_8);
        original.removeTask(TASK_1);
        jsonTaskifyStorage.saveAddressBook(original, filePath);
        readBack = jsonTaskifyStorage.readAddressBook(filePath).get();
        assertEquals(original, new Taskify(readBack));

        // Save and read without specifying file path
        original.addTask(TASK_9);
        jsonTaskifyStorage.saveAddressBook(original); // file path not specified
        readBack = jsonTaskifyStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new Taskify(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskify} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyTaskify taskify, String filePath) {
        try {
            new JsonTaskifyStorage(Paths.get(filePath))
                    .saveAddressBook(taskify, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Taskify(), null));
    }
}
