package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.ALICE;
import static seedu.address.testutil.TypicalTutors.HOON;
import static seedu.address.testutil.TypicalTutors.IDA;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.TutorBook;

public class JsonTutorBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTutorBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTutorBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTutorBook(null));
    }

    private java.util.Optional<ReadOnlyTutorBook> readTutorBook(String filePath) throws Exception {
        return new JsonTutorBookStorage(Paths.get(filePath)).readTutorBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTutorBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTutorBook("notJsonFormatTutorBook.json"));
    }

    @Test
    public void readTutorBook_invalidTutorTutorBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTutorBook("invalidTutorTutorBook.json"));
    }

    @Test
    public void readTutorBook_invalidAndValidTutorTutorBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTutorBook("invalidAndValidTutorTutorBook.json"));
    }

    @Test
    public void readAndSaveTutorBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTutorBook.json");
        TutorBook original = getTypicalTutorBook();
        JsonTutorBookStorage jsonTutorBookStorage = new JsonTutorBookStorage(filePath);

        // Save in new file and read back
        jsonTutorBookStorage.saveTutorBook(original, filePath);
        ReadOnlyTutorBook readBack = jsonTutorBookStorage.readTutorBook(filePath).get();
        assertEquals(original, new TutorBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTutor(HOON);
        original.removeTutor(ALICE);
        jsonTutorBookStorage.saveTutorBook(original, filePath);
        readBack = jsonTutorBookStorage.readTutorBook(filePath).get();
        assertEquals(original, new TutorBook(readBack));

        // Save and read without specifying file path
        original.addTutor(IDA);
        jsonTutorBookStorage.saveTutorBook(original); // file path not specified
        readBack = jsonTutorBookStorage.readTutorBook().get(); // file path not specified
        assertEquals(original, new TutorBook(readBack));

    }

    @Test
    public void saveTutorBook_nullTutorBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTutorBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tutorBook} at the specified {@code filePath}.
     */
    private void saveTutorBook(ReadOnlyTutorBook tutorBook, String filePath) {
        try {
            new JsonTutorBookStorage(Paths.get(filePath))
                    .saveTutorBook(tutorBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTutorBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTutorBook(new TutorBook(), null));
    }
}
