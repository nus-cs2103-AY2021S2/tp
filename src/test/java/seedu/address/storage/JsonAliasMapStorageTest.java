package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommandAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.ALIAS_ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.ALIAS_DELETE_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAliasMap;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.UniqueAliasMap;

public class JsonAliasMapStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAliasMapStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAliasMap_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAliasMap(null));
    }

    private java.util.Optional<ReadOnlyUniqueAliasMap> readAliasMap(String filePath) throws Exception {
        return new JsonAliasMapStorage(Paths.get(filePath)).readAliasMap(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAliasMap("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAliasMap("notJsonFormatAliasMap.json"));
    }

    @Test
    public void readAliasMap_invalidCommandAliasAliasMap_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAliasMap("invalidCommandAliasesAliasMap.json"));
    }

    @Test
    public void readAliasMap_invalidAndValidCommandAliasAliasMap_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAliasMap("invalidAndValidCommandAliasesAliasMap.json"));
    }

    @Test
    public void readAndSaveAliasMap_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAliasMap.json");
        UniqueAliasMap original = getTypicalAliasMap();
        JsonAliasMapStorage jsonAliasMapStorage = new JsonAliasMapStorage(filePath);

        // Save in new file and read back
        jsonAliasMapStorage.saveAliasMap(original, filePath);
        ReadOnlyUniqueAliasMap readBack = jsonAliasMapStorage.readAliasMap(filePath).get();
        assertEquals(original, new UniqueAliasMap(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAlias(ALIAS_ADD_COMMAND_ALIAS);
        original.removeAlias(ADD_ALIAS);
        jsonAliasMapStorage.saveAliasMap(original, filePath);
        readBack = jsonAliasMapStorage.readAliasMap(filePath).get();
        assertEquals(original, new UniqueAliasMap(readBack));

        // Save and read without specifying file path
        original.addAlias(ALIAS_DELETE_COMMAND_ALIAS);
        jsonAliasMapStorage.saveAliasMap(original); // file path not specified
        readBack = jsonAliasMapStorage.readAliasMap().get(); // file path not specified
        assertEquals(original, new UniqueAliasMap(readBack));
    }

    @Test
    public void saveAliasMap_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAliasMap(null, "SomeFile.json"));
    }

    /**
     * Saves {@code aliasMap} at the specified {@code filePath}.
     */
    private void saveAliasMap(ReadOnlyUniqueAliasMap aliasMap, String filePath) {
        try {
            new JsonAliasMapStorage(Paths.get(filePath))
                    .saveAliasMap(aliasMap, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAliasMap_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAliasMap(new UniqueAliasMap(), null));
    }
}
