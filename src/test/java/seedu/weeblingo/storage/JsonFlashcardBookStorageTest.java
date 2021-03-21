package seedu.weeblingo.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

// import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;

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

    //    @Test
    //    public void read_notJsonFormat_exceptionThrown() {
    //        assertThrows(DataConversionException.class, () -> readFlashcardBook("notJsonFormatBook.json"));
    //    }
    //
    //    @Test
    //    public void readFlashcardBook_invalidFlashcardBook_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, () -> readFlashcardBook("invalidFlashcardBook.json"));
    //    }

    //    @Test
    //    public void readFlashcardBook_invalidAndValidFlashcardBook_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, () -> readFlashcardBook(
    //                "invalidAndValidFlashcardBook.json"));
    //    }

    //    @Test
    //    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
    //        Path filePath = testFolder.resolve("TempAddressBook.json");
    //        FlashcardBook original = getTypicalAddressBook();
    //        JsonFlashcardBookStorage jsonAddressBookStorage = new JsonFlashcardBookStorage(filePath);
    //
    //        // Save in new file and read back
    //        jsonAddressBookStorage.saveAddressBook(original, filePath);
    //        ReadOnlyFlashcardBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
    //        assertEquals(original, new FlashcardBook(readBack));
    //
    //        // Modify data, overwrite exiting file, and read back
    //        original.addFlashcard(HOON);
    //        original.removeFlashcard(ALICE);
    //        jsonAddressBookStorage.saveAddressBook(original, filePath);
    //        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
    //        assertEquals(original, new FlashcardBook(readBack));
    //
    //        // Save and read without specifying file path
    //        original.addFlashcard(IDA);
    //        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
    //        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
    //        assertEquals(original, new FlashcardBook(readBack));
    //
    //    }

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
