package seedu.heymatez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.heymatez.testutil.Assert.assertThrows;
import static seedu.heymatez.testutil.TypicalPersons.ALICE;
import static seedu.heymatez.testutil.TypicalPersons.HOON;
import static seedu.heymatez.testutil.TypicalPersons.IDA;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.heymatez.commons.exceptions.DataConversionException;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.ReadOnlyHeyMatez;

public class JsonHeyMatezStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHeyMatezStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHeyMatez_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHeyMatez(null));
    }

    private java.util.Optional<ReadOnlyHeyMatez> readHeyMatez(String filePath) throws Exception {
        return new JsonHeyMatezStorage(Paths.get(filePath)).readHeyMatez(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHeyMatez("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHeyMatez("notJsonFormatHeyMatez.json"));
    }

    @Test
    public void readHeyMatez_invalidPersonHeyMatez_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHeyMatez("invalidPersonHeyMatez.json"));
    }

    @Test
    public void readHeyMatez_invalidAndValidPersonHeyMatez_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHeyMatez("invalidAndValidPersonHeyMatez.json"));
    }

    @Test
    public void readAndSaveHeyMatez_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHeyMatez.json");
        HeyMatez original = getTypicalHeyMatez();
        JsonHeyMatezStorage jsonHeyMatezStorage = new JsonHeyMatezStorage(filePath);

        // Save in new file and read back
        jsonHeyMatezStorage.saveHeyMatez(original, filePath);
        ReadOnlyHeyMatez readBack = jsonHeyMatezStorage.readHeyMatez(filePath).get();
        assertEquals(original, new HeyMatez(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonHeyMatezStorage.saveHeyMatez(original, filePath);
        readBack = jsonHeyMatezStorage.readHeyMatez(filePath).get();
        assertEquals(original, new HeyMatez(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonHeyMatezStorage.saveHeyMatez(original); // file path not specified
        readBack = jsonHeyMatezStorage.readHeyMatez().get(); // file path not specified
        assertEquals(original, new HeyMatez(readBack));

    }

    @Test
    public void saveHeyMatez_nullHeyMatez_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHeyMatez(null, "SomeFile.json"));
    }

    /**
     * Saves {@code heyMatez} at the specified {@code filePath}.
     */
    private void saveHeyMatez(ReadOnlyHeyMatez heyMatez, String filePath) {
        try {
            new JsonHeyMatezStorage(Paths.get(filePath))
                    .saveHeyMatez(heyMatez, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHeyMatez_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHeyMatez(new HeyMatez(), null));
    }
}
