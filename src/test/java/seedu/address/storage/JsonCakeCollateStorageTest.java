package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.ALICE;
import static seedu.address.testutil.TypicalOrders.HOON;
import static seedu.address.testutil.TypicalOrders.IDA;
import static seedu.address.testutil.TypicalOrders.getTypicalCakeCollate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CakeCollate;
import seedu.address.model.ReadOnlyCakeCollate;

public class JsonCakeCollateStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCakeCollateStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCakeCollate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCakeCollate(null));
    }

    private java.util.Optional<ReadOnlyCakeCollate> readCakeCollate(String filePath) throws Exception {
        return new JsonCakeCollateStorage(Paths.get(filePath)).readCakeCollate(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCakeCollate("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCakeCollate("notJsonFormatCakeCollate.json"));
    }

    @Test
    public void readCakeCollate_invalidOrderCakeCollate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCakeCollate("invalidOrderCakeCollate.json"));
    }

    @Test
    public void readCakeCollate_invalidAndValidOrderCakeCollate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCakeCollate("invalidAndValidOrderCakeCollate.json"));
    }

    @Test
    public void readAndSaveCakeCollate_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCakeCollate.json");
        CakeCollate original = getTypicalCakeCollate();
        JsonCakeCollateStorage jsonCakeCollateStorage = new JsonCakeCollateStorage(filePath);

        // Save in new file and read back
        jsonCakeCollateStorage.saveCakeCollate(original, filePath);
        ReadOnlyCakeCollate readBack = jsonCakeCollateStorage.readCakeCollate(filePath).get();
        assertEquals(original, new CakeCollate(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addOrder(HOON);
        original.removeOrder(ALICE);
        jsonCakeCollateStorage.saveCakeCollate(original, filePath);
        readBack = jsonCakeCollateStorage.readCakeCollate(filePath).get();
        assertEquals(original, new CakeCollate(readBack));

        // Save and read without specifying file path
        original.addOrder(IDA);
        jsonCakeCollateStorage.saveCakeCollate(original); // file path not specified
        readBack = jsonCakeCollateStorage.readCakeCollate().get(); // file path not specified
        assertEquals(original, new CakeCollate(readBack));

    }

    @Test
    public void saveCakeCollate_nullCakeCollate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCakeCollate(null, "SomeFile.json"));
    }

    /**
     * Saves {@code cakeCollate} at the specified {@code filePath}.
     */
    private void saveCakeCollate(ReadOnlyCakeCollate cakeCollate, String filePath) {
        try {
            new JsonCakeCollateStorage(Paths.get(filePath))
                    .saveCakeCollate(cakeCollate, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCakeCollate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCakeCollate(new CakeCollate(), null));
    }
}
