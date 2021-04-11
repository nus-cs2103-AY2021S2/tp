package seedu.flashback.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalFlashcards.DARWIN;
import static seedu.flashback.testutil.TypicalFlashcards.IDA;
import static seedu.flashback.testutil.TypicalFlashcards.PYTHAGOREAN;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.flashback.commons.exceptions.DataConversionException;
import seedu.flashback.model.FlashBack;
import seedu.flashback.model.ReadOnlyFlashBack;

public class JsonFlashBackStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFlashBackStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFlashBack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFlashBack(null));
    }

    private java.util.Optional<ReadOnlyFlashBack> readFlashBack(String filePath) throws Exception {
        return new JsonFlashBackStorage(Paths.get(filePath)).readFlashBack(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFlashBack("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFlashBack("notJsonFormatFlashBack.json"));
    }

    @Test
    public void readFlashBack_invalidFlashBack_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFlashBack("invalidFlashcardFlashBack.json"));
    }

    @Test
    public void readFlashBack_invalidAndValidFlashBack_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFlashBack("invalidAndValidFlashcardFlashBack.json"));
    }

    @Test
    public void readAndSaveFlashBack_allInOrder_success() throws Exception {
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
    public void saveFlashBack_nullFlashBack_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashBack(null, "SomeFile.json"));
    }

    /**
     * Saves {@code flashBack} at the specified {@code filePath}.
     */
    private void saveFlashBack(ReadOnlyFlashBack flashBack, String filePath) {
        try {
            new JsonFlashBackStorage(Paths.get(filePath))
                    .saveFlashBack(flashBack, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFlashBack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashBack(new FlashBack(), null));
    }
}
