package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;

public class JsonResidenceTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonResidenceTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readResidenceTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readResidenceTracker(null));
    }

    private java.util.Optional<ReadOnlyResidenceTracker> readResidenceTracker(String filePath) throws Exception {
        return new JsonResidenceTrackerStorage(Paths.get(filePath))
            .readResidenceTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readResidenceTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readResidenceTracker("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResidenceTracker("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResidenceTracker("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ResidenceTracker original = getTypicalAddressBook();
        JsonResidenceTrackerStorage jsonAddressBookStorage = new JsonResidenceTrackerStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveResidenceTracker(original, filePath);
        ReadOnlyResidenceTracker readBack = jsonAddressBookStorage.readResidenceTracker(filePath).get();
        assertEquals(original, new ResidenceTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveResidenceTracker(original, filePath);
        readBack = jsonAddressBookStorage.readResidenceTracker(filePath).get();
        assertEquals(original, new ResidenceTracker(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveResidenceTracker(original); // file path not specified
        readBack = jsonAddressBookStorage.readResidenceTracker().get(); // file path not specified
        assertEquals(original, new ResidenceTracker(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyResidenceTracker addressBook, String filePath) {
        try {
            new JsonResidenceTrackerStorage(Paths.get(filePath))
                    .saveResidenceTracker(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new ResidenceTracker(), null));
    }
}
