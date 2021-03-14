package seedu.storemando.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.CHAIR;
import static seedu.storemando.testutil.TypicalItems.MILK;
import static seedu.storemando.testutil.TypicalItems.TOOTHBRUSH;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;

public class JsonStoreMandoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
        "JsonStoreMandoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStoreMando_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStoreMando(null));
    }

    private java.util.Optional<ReadOnlyStoreMando> readStoreMando(String filePath) throws Exception {
        return new JsonStoreMandoStorage(Paths.get(filePath)).readStoreMando(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStoreMando("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readStoreMando("notJsonFormatStoreMando.json"));
    }

    @Test
    public void readStoreMando_invalidItemStoreMando_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStoreMando("invalidItemStoreMando.json"));
    }

    @Test
    public void readStoreMando_invalidAndValidItemStoreMando_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStoreMando("invalidAndValidItemStoreMando.json"));
    }

    @Test
    public void readAndSaveStoreMando_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStoreMando.json");
        StoreMando original = getTypicalStoreMando();
        JsonStoreMandoStorage jsonStoreMandoStorage = new JsonStoreMandoStorage(filePath);

        // Save in new file and read back
        jsonStoreMandoStorage.saveStoreMando(original, filePath);
        ReadOnlyStoreMando readBack = jsonStoreMandoStorage.readStoreMando(filePath).get();
        assertEquals(original, new StoreMando(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(TOOTHBRUSH);
        original.removeItem(MILK);
        jsonStoreMandoStorage.saveStoreMando(original, filePath);
        readBack = jsonStoreMandoStorage.readStoreMando(filePath).get();
        assertEquals(original, new StoreMando(readBack));

        // Save and read without specifying file path
        original.addItem(CHAIR);
        jsonStoreMandoStorage.saveStoreMando(original); // file path not specified
        readBack = jsonStoreMandoStorage.readStoreMando().get(); // file path not specified
        assertEquals(original, new StoreMando(readBack));

    }

    @Test
    public void saveStoreMando_nullStoreMando_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStoreMando(null, "SomeFile.json"));
    }

    /**
     * Saves {@code storeMando} at the specified {@code filePath}.
     */
    private void saveStoreMando(ReadOnlyStoreMando storeMando, String filePath) {
        try {
            new JsonStoreMandoStorage(Paths.get(filePath))
                .saveStoreMando(storeMando, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStoreMando_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStoreMando(new StoreMando(), null));
    }
}
