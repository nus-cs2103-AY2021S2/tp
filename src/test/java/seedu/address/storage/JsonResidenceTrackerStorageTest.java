package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalResidences.EXTRA_R1;
//import static seedu.address.testutil.TypicalResidences.EXTRA_R2;
//import static seedu.address.testutil.TypicalResidences.RESIDENCE_A;
//import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

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
        assertThrows(DataConversionException.class, () -> readResidenceTracker("notJsonFormatResidenceTracker.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readResidenceTracker("invalidResidenceResidenceTracker.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResidenceTracker(
                "invalidAndValidResidenceResidenceTracker.json"));
    }

    /*@Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempResidenceTracker.json");
        ResidenceTracker original = getTypicalResidenceTracker();
        JsonResidenceTrackerStorage jsonResidenceTrackerStorage = new JsonResidenceTrackerStorage(filePath);

        // Save in new file and read back
        jsonResidenceTrackerStorage.saveResidenceTracker(original, filePath);
        ReadOnlyResidenceTracker readBack = jsonResidenceTrackerStorage.readResidenceTracker(filePath).get();
        assertEquals(original, new ResidenceTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addResidence(EXTRA_R1);
        original.removeResidence(RESIDENCE_A);
        jsonResidenceTrackerStorage.saveResidenceTracker(original, filePath);
        readBack = jsonResidenceTrackerStorage.readResidenceTracker(filePath).get();
        assertEquals(original, new ResidenceTracker(readBack));

        // Save and read without specifying file path
        original.addResidence(EXTRA_R2);
        jsonResidenceTrackerStorage.saveResidenceTracker(original); // file path not specified
        readBack = jsonResidenceTrackerStorage.readResidenceTracker().get(); // file path not specified
        assertEquals(original, new ResidenceTracker(readBack));

    }*/

    @Test
    public void saveResidenceTracker_nullResidenceTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveResidenceTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker, String filePath) {
        try {
            new JsonResidenceTrackerStorage(Paths.get(filePath))
                    .saveResidenceTracker(residenceTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveResidenceTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveResidenceTracker(new ResidenceTracker(), null));
    }
}
