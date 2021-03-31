package dog.pawbook.storage;

import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.HOON;
import static dog.pawbook.testutil.TypicalOwners.IDA;
import static dog.pawbook.testutil.TypicalOwners.getTypicalDatabase;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dog.pawbook.commons.exceptions.DataConversionException;
import dog.pawbook.model.Database;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.managedentity.Entity;
import javafx.util.Pair;

public class JsonDatabaseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDatabaseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDatabase_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDatabase(null));
    }

    private java.util.Optional<ReadOnlyDatabase> readDatabase(String filePath) throws Exception {
        return new JsonDatabaseStorage(Paths.get(filePath)).readDatabase(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDatabase("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readDatabase("notJsonFormatDatabase.json"));
    }

    @Test
    public void readDatabase_invalidOwnerDatabase_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDatabase("invalidOwnerDatabase.json"));
    }

    @Test
    public void readDatabase_invalidAndValidOwnerDatabase_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDatabase("invalidAndValidOwnerDatabase.json"));
    }

    @Test
    public void readAndSaveDatabase_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDatabase.json");
        Database original = getTypicalDatabase();
        JsonDatabaseStorage jsonDatabaseStorage = new JsonDatabaseStorage(filePath);

        // Save in new file and read back
        jsonDatabaseStorage.saveDatabase(original, filePath);
        ReadOnlyDatabase readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEntity(HOON);
        List<Pair<Integer, Entity>> targets = original.getEntityList().stream()
                .filter(p -> p.getValue().equals(ALICE))
                .collect(toList());
        int aliceId = targets.get(0).getKey();
        original.removeEntity(aliceId);
        jsonDatabaseStorage.saveDatabase(original, filePath);
        readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Save and read without specifying file path
        original.addEntity(IDA);
        jsonDatabaseStorage.saveDatabase(original); // file path not specified
        readBack = jsonDatabaseStorage.readDatabase().get(); // file path not specified
        assertEquals(original, new Database(readBack));

    }

    @Test
    public void saveDatabase_nullDatabase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatabase(null, "SomeFile.json"));
    }

    /**
     * Saves {@code database} at the specified {@code filePath}.
     */
    private void saveDatabase(ReadOnlyDatabase database, String filePath) {
        try {
            new JsonDatabaseStorage(Paths.get(filePath))
                    .saveDatabase(database, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDatabase_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatabase(new Database(), null));
    }
}
