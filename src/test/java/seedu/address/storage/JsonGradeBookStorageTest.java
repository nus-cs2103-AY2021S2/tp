package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGrades.MATHS_GRADE;
import static seedu.address.testutil.TypicalGrades.PHYSICS_GRADE;
import static seedu.address.testutil.TypicalGrades.HISTORY_GRADE;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyGradeBook;

public class JsonGradeBookStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonGradeBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGradeBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGradeBook(null));
    }

    private java.util.Optional<ReadOnlyGradeBook> readGradeBook(String filePath) throws Exception {
        return new JsonGradeBookStorage(Paths.get(filePath)).readGradeBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGradeBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readGradeBook("notJsonFormatGradeBook.json"));
    }

    @Test
    public void readGradeBook_invalidGradeGradeBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGradeBook("invalidGradeGradeBook.json"));
    }

    @Test
    public void readGradeBook_invalidAndValidGradeGradeBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGradeBook("invalidAndValidGradeGradeBook.json"));
    }

    @Test
    public void readAndSaveGradeBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGradeBook.json");
        GradeBook original = getTypicalGradeBook();
        JsonGradeBookStorage jsonGradeBookStorage = new JsonGradeBookStorage(filePath);

        // Save in new file and read back
        jsonGradeBookStorage.saveGradeBook(original, filePath);
        ReadOnlyGradeBook readBack = jsonGradeBookStorage.readGradeBook(filePath).get();
        assertEquals(original, new GradeBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGrade(PHYSICS_GRADE);
        original.removeGrade(MATHS_GRADE);
        jsonGradeBookStorage.saveGradeBook(original, filePath);
        readBack = jsonGradeBookStorage.readGradeBook(filePath).get();
        assertEquals(original, new GradeBook(readBack));

        // Save and read without specifying file path
        original.addGrade(HISTORY_GRADE);
        jsonGradeBookStorage.saveGradeBook(original); // file path not specified
        readBack = jsonGradeBookStorage.readGradeBook().get(); // file path not specified
        assertEquals(original, new GradeBook(readBack));

    }

    @Test
    public void saveGradeBook_nullGradeBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGradeBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tutorBook} at the specified {@code filePath}.
     */
    private void saveGradeBook(ReadOnlyGradeBook gradeBook, String filePath) {
        try {
            new JsonGradeBookStorage(Paths.get(filePath))
                    .saveGradeBook(gradeBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGradeBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGradeBook(new GradeBook(), null));
    }
}
