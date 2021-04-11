package seedu.module.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.FINAL;
import static seedu.module.testutil.TypicalTasks.MISSION;
import static seedu.module.testutil.TypicalTasks.QUIZ;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.module.commons.exceptions.DataConversionException;
import seedu.module.model.ModuleBook;
import seedu.module.model.ReadOnlyModuleBook;

public class JsonModuleBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModuleBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readModuleBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readModuleBook(null));
    }

    private java.util.Optional<ReadOnlyModuleBook> readModuleBook(String filePath) throws Exception {
        return new JsonModuleBookStorage(Paths.get(filePath)).readModuleBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModuleBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readModuleBook("notJsonFormatModuleBook.json"));
    }

    @Test
    public void readModuleBook_invalidTaskModuleBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleBook("invalidTaskModuleBook.json"));
    }

    @Test
    public void readModuleBook_invalidAndValidTaskModuleBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleBook("invalidAndValidTaskModuleBook.json"));
    }

    @Test
    public void readAndSaveModuleBook_allInOrder_success() throws Exception {

        Path filePath = testFolder.resolve("TempModuleBook.json");
        ModuleBook original = getTypicalModuleBook();
        JsonModuleBookStorage jsonModuleBookStorage = new JsonModuleBookStorage(filePath);

        // Save in new file and read back
        jsonModuleBookStorage.saveModuleBook(original, filePath);
        ReadOnlyModuleBook readBack = jsonModuleBookStorage.readModuleBook(filePath).get();
        assertEquals(original, new ModuleBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(MISSION);
        original.removeTask(QUIZ);
        jsonModuleBookStorage.saveModuleBook(original, filePath);
        readBack = jsonModuleBookStorage.readModuleBook(filePath).get();
        assertEquals(original, new ModuleBook(readBack));

        // Save and read without specifying file path
        original.addTask(FINAL);
        jsonModuleBookStorage.saveModuleBook(original); // file path not specified
        readBack = jsonModuleBookStorage.readModuleBook().get(); // file path not specified
        assertEquals(original, new ModuleBook(readBack));
    }

    @Test
    public void saveModuleBook_nullModuleBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code moduleBook} at the specified {@code filePath}.
     */
    private void saveModuleBook(ReadOnlyModuleBook moduleBook, String filePath) {
        try {
            new JsonModuleBookStorage(Paths.get(filePath))
                    .saveModuleBook(moduleBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveModuleBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleBook(new ModuleBook(), null));
    }
}
