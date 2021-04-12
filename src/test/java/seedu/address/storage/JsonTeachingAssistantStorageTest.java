package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeachingAssistant.AVA;
import static seedu.address.testutil.TypicalTeachingAssistant.CONSULTATION_MATH;
import static seedu.address.testutil.TypicalTeachingAssistant.HANNAH;
import static seedu.address.testutil.TypicalTeachingAssistant.IVAN;
import static seedu.address.testutil.TypicalTeachingAssistant.REMEDIAL;
import static seedu.address.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachingAssistant;
import seedu.address.model.TeachingAssistant;

public class JsonTeachingAssistantStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTeachingAssistantStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTeachingAssistant_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTeachingAssistant(null));
    }

    private java.util.Optional<ReadOnlyTeachingAssistant> readTeachingAssistant(String filePath) throws Exception {
        return new JsonTeachingAssistantStorage(Paths.get(filePath))
                .readTeachingAssistant(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTeachingAssistant("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTeachingAssistant("notJsonFormatTeachingAssistant.json"));
    }

    @Test
    public void readTeachingAssistant_invalidContactTeachingAssistant_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachingAssistant(
                "invalidContactTeachingAssistant.json"));
    }

    @Test
    public void readTeachingAssistant_invalidAndValidContactTeachingAssistant_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachingAssistant(
                "invalidAndValidTeachingAssistant.json"));
    }

    @Test
    public void readTeachingAssistant_invalidEntryTeachingAssistant_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachingAssistant(
                "invalidEntryTeachingAssistant.json"));
    }

    @Test
    public void readTeachingAssistant_invalidAndValidEntryTeachingAssistant_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTeachingAssistant("invalidAndValidEntryTeachingAssistant.json"));
    }

    @Test
    public void readAndSaveTeachingAssistant_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTeachingAssistant.json");
        TeachingAssistant original = getTypicalTeachingAssistant();
        JsonTeachingAssistantStorage jsonTeachingAssistantStorage = new JsonTeachingAssistantStorage(filePath);

        // Save in new file and read back
        jsonTeachingAssistantStorage.saveTeachingAssistant(original, filePath);
        ReadOnlyTeachingAssistant readBack = jsonTeachingAssistantStorage.readTeachingAssistant(filePath).get();
        assertEquals(original, new TeachingAssistant(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HANNAH);
        original.removeContact(AVA);
        original.addEntry(REMEDIAL);
        jsonTeachingAssistantStorage.saveTeachingAssistant(original, filePath);
        readBack = jsonTeachingAssistantStorage.readTeachingAssistant(filePath).get();
        assertEquals(original, new TeachingAssistant(readBack));

        // Save and read without specifying file path
        original.addContact(IVAN);
        original.addEntry(CONSULTATION_MATH);
        jsonTeachingAssistantStorage.saveTeachingAssistant(original); // file path not specified
        readBack = jsonTeachingAssistantStorage.readTeachingAssistant().get(); // file path not specified
        assertEquals(original, new TeachingAssistant(readBack));

    }

    @Test
    public void saveTeachingAssistant_nullTeachingAssistant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeachingAssistant(null, "SomeFile.json"));
    }

    /**
     * Saves {@code teachingAssistant} at the specified {@code filePath}.
     */
    private void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant, String filePath) {
        try {
            new JsonTeachingAssistantStorage(Paths.get(filePath))
                    .saveTeachingAssistant(teachingAssistant, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTeachingAssistant_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeachingAssistant(new TeachingAssistant(), null));
    }
}
