package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalAliases.ALIAS_ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalAliases.ALIAS_DELETE_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.UniqueAliasMap;

public class JsonAliasesStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAliasesStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAliases_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAliases(null));
    }

    private java.util.Optional<ReadOnlyUniqueAliasMap> readAliases(String filePath) throws Exception {
        return new JsonAliasesStorage(Paths.get(filePath)).readAliases(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAliases("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAliases("notJsonFormatAliases.json"));
    }

    @Test
    public void readAliases_invalidAliasAliases_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAliases("invalidAliasAliases.json"));
    }

    @Test
    public void readAliases_invalidAndValidAliasAliases_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAliases("invalidAndValidAliasAliases.json"));
    }

    @Test
    public void readAndSaveAliases_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAliases.json");
        UniqueAliasMap original = getTypicalAliases();
        JsonAliasesStorage jsonAliasesStorage = new JsonAliasesStorage(filePath);

        // Save in new file and read back
        jsonAliasesStorage.saveAliases(original, filePath);
        ReadOnlyUniqueAliasMap readBack = jsonAliasesStorage.readAliases(filePath).get();
        assertEquals(original, new UniqueAliasMap(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAlias(ALIAS_ADD_COMMAND_ALIAS);
        original.removeAlias(ADD_ALIAS);
        jsonAliasesStorage.saveAliases(original, filePath);
        readBack = jsonAliasesStorage.readAliases(filePath).get();
        assertEquals(original, new UniqueAliasMap(readBack));

        // Save and read without specifying file path
        original.addAlias(ALIAS_DELETE_COMMAND_ALIAS);
        jsonAliasesStorage.saveAliases(original); // file path not specified
        readBack = jsonAliasesStorage.readAliases().get(); // file path not specified
        assertEquals(original, new UniqueAliasMap(readBack));
    }

    @Test
    public void saveAliases_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAliases(null, "SomeFile.json"));
    }

    /**
     * Saves {@code aliases} at the specified {@code filePath}.
     */
    private void saveAliases(ReadOnlyUniqueAliasMap aliases, String filePath) {
        try {
            new JsonAliasesStorage(Paths.get(filePath))
                    .saveAliases(aliases, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAliases_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAliases(new UniqueAliasMap(), null));
    }
}
