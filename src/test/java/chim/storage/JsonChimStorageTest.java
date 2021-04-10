package chim.storage;

import static chim.testutil.Assert.assertThrows;
import static chim.testutil.TypicalCustomers.ALICE;
import static chim.testutil.TypicalCustomers.HOON;
import static chim.testutil.TypicalCustomers.IDA;
import static chim.testutil.TypicalModels.getTypicalChim;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import chim.commons.exceptions.DataConversionException;
import chim.model.Chim;
import chim.model.ReadOnlyChim;

public class JsonChimStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonChimStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readChim_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readChim(null));
    }

    private java.util.Optional<ReadOnlyChim> readChim(String filePath) throws Exception {
        return new JsonChimStorage(Paths.get(filePath)).readChim(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readChim("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readChim("notJsonFormatChim.json"));
    }

    @Test
    public void readChim_invalidCustomerChim_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readChim("invalidCustomerChim.json"));
    }

    @Test
    public void readChim_invalidAndValidCustomerChim_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readChim("invalidAndValidCustomerChim.json"));
    }

    @Test
    public void readAndSaveChim_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempChim.json");
        Chim original = getTypicalChim();
        JsonChimStorage jsonChimStorage = new JsonChimStorage(filePath);

        // Save in new file and read back
        jsonChimStorage.saveChim(original, filePath);
        ReadOnlyChim readBack = jsonChimStorage.readChim(filePath).get();
        assertEquals(original, new Chim(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCustomer(HOON);
        original.deleteCustomer(ALICE);
        jsonChimStorage.saveChim(original, filePath);
        readBack = jsonChimStorage.readChim(filePath).get();
        assertEquals(original, new Chim(readBack));

        // Save and read without specifying file path
        original.addCustomer(IDA);
        jsonChimStorage.saveChim(original); // file path not specified
        readBack = jsonChimStorage.readChim().get(); // file path not specified
        assertEquals(original, new Chim(readBack));

    }

    @Test
    public void saveChim_nullChim_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveChim(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Chim} at the specified {@code filePath}.
     */
    private void saveChim(ReadOnlyChim chim, String filePath) {
        try {
            new JsonChimStorage(Paths.get(filePath))
                    .saveChim(chim, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveChim_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveChim(new Chim(), null));
    }
}
