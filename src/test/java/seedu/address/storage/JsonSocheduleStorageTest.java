package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.Sochedule;

public class JsonSocheduleStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSocheduleStorageTest");

    @TempDir
    public Path testFolder;

    private java.util.Optional<ReadOnlySochedule> readSochedule(String filePath) throws Exception {
        return new JsonSocheduleStorage(Paths.get(filePath)).readSochedule(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readSochedule_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSochedule(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSochedule("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSochedule("notJsonFormatSochedule.json"));
    }

    @Test
    public void readAddressBook_invalidEventSochedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSochedule("invalidEventSochedule.json"));
    }

    @Test
    public void readAddressBook_invalidTaskSochedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSochedule("invalidTaskSochedule.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidEventSochedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSochedule("invalidAndValidEventSochedule.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidTaskSochedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSochedule("invalidAndValidTaskSochedule.json"));
    }

    /**
     * Saves {@code Sochedule} at the specified {@code filePath}.
     */
    private void saveSochedule(ReadOnlySochedule addressBook, String filePath) {
        try {
            new JsonSocheduleStorage(Paths.get(filePath))
                    .saveSochedule(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSochedule(new Sochedule(), null));
    }

    @Test
    public void saveSochedule_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSochedule(null, "SomeFile.json"));
    }

    /*
    @Test
    // PENDING TypicalSochedule
    public void readAndSaveSochedule_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSochedule.json");
        Sochedule original = getTypicalSochedule();
        JsonSocheduleStorage jsonSocheduleStorage = new JsonSocheduleStorage(filePath);

        // Save in new file and read back
        jsonSocheduleStorage.saveSochedule(original, filePath);
        ReadOnlySochedule readBack = jsonSocheduleStorage.readSochedule(filePath).get();
        assertEquals(original, new Sochedule(readBack));

        // Modify data, overwrite exiting file, and read back
        // NEED RELOOK TO GET VAR NAMES
        original.addEvent(HOON);
        original.addTask(HOON);
        original.removeEvent(ALICE);
        original.removeTask(ALICE);
        jsonSocheduleStorage.saveSochedule(original, filePath);
        readBack = jsonSocheduleStorage.readAddressBook(filePath).get();
        assertEquals(original, new Sochedule(readBack));

        // Save and read without specifying file path
        original.addTask(HOON);
        original.addEvent(HOON);
        jsonSocheduleStorage.saveSochedule(original); // file path not specified
        readBack = jsonSocheduleStorage.readSochedule().get(); // file path not specified
        assertEquals(original, new Sochedule(readBack));
    }
    */
}
