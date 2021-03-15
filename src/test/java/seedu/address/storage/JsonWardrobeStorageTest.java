package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.HOON;
import static seedu.address.testutil.TypicalGarments.IDA;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;

public class JsonWardrobeStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWardrobeStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWardrobe_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWardrobe(null));
    }

    private java.util.Optional<ReadOnlyWardrobe> readWardrobe(String filePath) throws Exception {
        return new JsonWardrobeStorage(Paths.get(filePath)).readWardrobe(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWardrobe("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readWardrobe("notJsonFormatWardrobe.json"));
    }

    @Test
    public void readWardrobe_invalidGarmentWardrobe_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWardrobe("invalidGarmentWardrobe.json"));
    }

    @Test
    public void readWardrobe_invalidAndValidGarmentWardrobe_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWardrobe("invalidAndValidGarmentWardrobe.json"));
    }

    @Test
    public void readAndSaveWardrobe_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWardrobe.json");
        Wardrobe original = getTypicalWardrobe();
        JsonWardrobeStorage jsonWardrobeStorage = new JsonWardrobeStorage(filePath);

        // Save in new file and read back
        jsonWardrobeStorage.saveWardrobe(original, filePath);
        ReadOnlyWardrobe readBack = jsonWardrobeStorage.readWardrobe(filePath).get();
        assertEquals(original, new Wardrobe(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGarment(HOON);
        original.removeGarment(ALICE);
        jsonWardrobeStorage.saveWardrobe(original, filePath);
        readBack = jsonWardrobeStorage.readWardrobe(filePath).get();
        assertEquals(original, new Wardrobe(readBack));

        // Save and read without specifying file path
        original.addGarment(IDA);
        jsonWardrobeStorage.saveWardrobe(original); // file path not specified
        readBack = jsonWardrobeStorage.readWardrobe().get(); // file path not specified
        assertEquals(original, new Wardrobe(readBack));

    }

    @Test
    public void saveWardrobe_nullWardrobe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWardrobe(null, "SomeFile.json"));
    }

    /**
     * Saves {@code wardrobe} at the specified {@code filePath}.
     */
    private void saveWardrobe(ReadOnlyWardrobe wardrobe, String filePath) {
        try {
            new JsonWardrobeStorage(Paths.get(filePath))
                    .saveWardrobe(wardrobe, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWardrobe_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWardrobe(new Wardrobe(), null));
    }
}
