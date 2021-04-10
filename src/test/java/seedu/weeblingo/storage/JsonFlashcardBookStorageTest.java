package seedu.weeblingo.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;
import static seedu.weeblingo.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

// import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;

/**
 * Test class for JsonFlashcardBookStorage.
 */
public class JsonFlashcardBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonFlashcardBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFlashcardBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFlashcardBook(null));
    }

    private java.util.Optional<ReadOnlyFlashcardBook> readFlashcardBook(String filePath) throws Exception {
        return new JsonFlashcardBookStorage(Paths.get(filePath))
                .readFlashcardBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFlashcardBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFlashcardBook("notJsonFormatBook.json"));
    }

    @Test
    public void readFlashcardBook_invalidFlashcardBook_throwDataConversionException() {

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook1.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook2.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook3.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook4.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook5.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook6.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook7.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook8.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook9.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook10.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook11.json"));

        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook12.json"));

    }

    @Test
    public void readFlashcardBook_invalidAndValidFlashcardBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFlashcardBook(
            "invalidAndValidFlashcardBook.json"));
    }

    @Test
    public void readAndSaveFlashcardBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFlashcardBook.json");
        FlashcardBook original = getTypicalFlashcardBook();
        JsonFlashcardBookStorage jsonAddressBookStorage = new JsonFlashcardBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveFlashcardBook(original, filePath);
        ReadOnlyFlashcardBook readBack = jsonAddressBookStorage.readFlashcardBook(filePath).get();
        assertEquals(original, new FlashcardBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeFlashcard(A_CARD);
        jsonAddressBookStorage.saveFlashcardBook(original, filePath);
        readBack = jsonAddressBookStorage.readFlashcardBook(filePath).get();
        assertEquals(original, new FlashcardBook(readBack));

        // Save and read without specifying file path
        jsonAddressBookStorage.saveFlashcardBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readFlashcardBook().get(); // file path not specified
        assertEquals(original, new FlashcardBook(readBack));
    }

    @Test
    public void saveFlashcardBook_nullFlashcardBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashcardBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code flashcardBook} at the specified {@code filePath}.
     */
    private void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook, String filePath) {
        try {
            new JsonFlashcardBookStorage(Paths.get(filePath))
                    .saveFlashcardBook(flashcardBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFlashcardBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashcardBook(new FlashcardBook(), null));
    }
}
