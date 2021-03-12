package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.DARWIN;
import static seedu.address.testutil.TypicalFlashcards.IDA;
import static seedu.address.testutil.TypicalFlashcards.PYTHAGOREAN;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FlashBack;
import seedu.address.model.ReadOnlyFlashBack;

public class JsonFlashBackStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFlashBackStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyFlashBack> readAddressBook(String filePath) throws Exception {
        return new JsonFlashBackStorage(Paths.get(filePath)).readFlashBack(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatFlashBack.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidFlashcardFlashBack.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidFlashcardFlashBack.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        FlashBack original = getTypicalFlashBack();
        JsonFlashBackStorage jsonFlashBackStorage = new JsonFlashBackStorage(filePath);

        // Save in new file and read back
        jsonFlashBackStorage.saveFlashBack(original, filePath);
        ReadOnlyFlashBack readBack = jsonFlashBackStorage.readFlashBack(filePath).get();
        assertEquals(original, new FlashBack(readBack));

        // Modify data, overwrite existing file, and read back
        original.addCard(DARWIN);
        original.removeCard(PYTHAGOREAN);
        jsonFlashBackStorage.saveFlashBack(original, filePath);
        readBack = jsonFlashBackStorage.readFlashBack(filePath).get();
        assertEquals(original, new FlashBack(readBack));

        // Save and read without specifying file path
        original.addCard(IDA);
        jsonFlashBackStorage.saveFlashBack(original); // file path not specified
        readBack = jsonFlashBackStorage.readFlashBack().get(); // file path not specified
        assertEquals(original, new FlashBack(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyFlashBack addressBook, String filePath) {
        try {
            new JsonFlashBackStorage(Paths.get(filePath))
                    .saveFlashBack(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new FlashBack(), null));
    }
}
