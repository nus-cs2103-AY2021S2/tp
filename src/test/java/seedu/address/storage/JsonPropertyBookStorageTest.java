package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.AMPANG_KITCHEN;
import static seedu.address.testutil.TypicalProperties.KAP_RESIDENCES;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyPropertyBook;

public class JsonPropertyBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPropertyBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPropertyBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPropertyBook(null));
    }

    private java.util.Optional<ReadOnlyPropertyBook> readPropertyBook(String filePath) throws Exception {
        return new JsonPropertyBookStorage(Paths.get(filePath))
                .readPropertyBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPropertyBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPropertyBook("notJsonFormatPropertyBook.json"));
    }

    @Test
    public void readPropertyBook_invalidPropertyInPropertyBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPropertyBook(
                "invalidPropertyInPropertyBook.json"));
    }

    @Test
    public void readPropertyBook_invalidAndValidPropertyInPropertyBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPropertyBook(
                "invalidAndValidPropertyInPropertyBook.json"));
    }

    @Test
    public void readAndSavePropertyBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPropertyBook.json");
        PropertyBook original = getTypicalPropertyBook();
        JsonPropertyBookStorage jsonPropertyBookStorage = new JsonPropertyBookStorage(filePath);

        // Save in new file and read back
        jsonPropertyBookStorage.savePropertyBook(original, filePath);
        ReadOnlyPropertyBook readBack = jsonPropertyBookStorage.readPropertyBook(filePath).get();
        assertEquals(original, new PropertyBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProperty(AMPANG_KITCHEN);
        original.removeProperty(WOODLANDS_CRESCENT);
        jsonPropertyBookStorage.savePropertyBook(original, filePath);
        readBack = jsonPropertyBookStorage.readPropertyBook(filePath).get();
        assertEquals(original, new PropertyBook(readBack));

        // Save and read without specifying file path
        original.addProperty(KAP_RESIDENCES);
        jsonPropertyBookStorage.savePropertyBook(original); // file path not specified
        readBack = jsonPropertyBookStorage.readPropertyBook().get(); // file path not specified
        assertEquals(original, new PropertyBook(readBack));

    }

    @Test
    public void savePropertyBook_nullPropertyBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePropertyBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code propertyBook} at the specified {@code filePath}.
     */
    private void savePropertyBook(ReadOnlyPropertyBook propertyBook, String filePath) {
        try {
            new JsonPropertyBookStorage(Paths.get(filePath))
                    .savePropertyBook(propertyBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePropertyBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePropertyBook(new PropertyBook(), null));
    }
}
