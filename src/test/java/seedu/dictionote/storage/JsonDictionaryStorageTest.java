package seedu.dictionote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalContent.WEEK_1;
import static seedu.dictionote.testutil.TypicalContent.WEEK_14;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.ReadOnlyDictionary;

public class JsonDictionaryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDictionaryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDictionary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDictionary(null));
    }

    private java.util.Optional<ReadOnlyDictionary> readDictionary(String filePath) throws Exception {
        return new JsonDictionaryStorage(Paths.get(filePath)).readDictionary(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDictionary("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readDictionary("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readDictionary_invalidContentDictionary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDictionary("invalidContentDictionary.json"));
    }

    @Test
    public void readDictionary_invalidAndValidContentDictionary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readDictionary("invalidAndValidContentDictionary.json"));
    }

    @Test
    public void readAndSaveDictionary_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDictionary.json");
        Dictionary original = getTypicalDictionary();
        JsonDictionaryStorage jsonDictionaryStorage = new JsonDictionaryStorage(filePath);

        // Save in new file and read back
        jsonDictionaryStorage.saveDictionary(original, filePath);
        ReadOnlyDictionary readBack = jsonDictionaryStorage.readDictionary(filePath).get();
        assertEquals(original, new Dictionary(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContent(WEEK_1);
        jsonDictionaryStorage.saveDictionary(original, filePath);
        readBack = jsonDictionaryStorage.readDictionary(filePath).get();
        assertEquals(original, new Dictionary(readBack));

        // Save and read without specifying file path
        original.addContent(WEEK_14);
        jsonDictionaryStorage.saveDictionary(original); // file path not specified
        readBack = jsonDictionaryStorage.readDictionary().get(); // file path not specified
        assertEquals(original, new Dictionary(readBack));

    }

    @Test
    public void saveDictionary_nullDictionary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDictionary(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveDictionary(ReadOnlyDictionary dictionary, String filePath) {
        try {
            new JsonDictionaryStorage(Paths.get(filePath))
                    .saveDictionary(dictionary, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDictionary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDictionary(new Dictionary(), null));
    }
}
