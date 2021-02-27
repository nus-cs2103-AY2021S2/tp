package seedu.us.among.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.us.among.commons.exceptions.DataConversionException;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.testutil.Assert;
import seedu.us.among.testutil.TypicalEndpoints;

public class JsonEndpointListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEndpointListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEndpointList_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readEndpointList(null));
    }

    private java.util.Optional<ReadOnlyEndpointList> readEndpointList(String filePath) throws Exception {
        return new JsonEndpointListStorage(Paths.get(filePath)).readEndpointList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEndpointList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readEndpointList("notJsonFormatEndpointList.json"));
    }

    @Test
    public void readEndpointList_invalidEndpointList_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readEndpointList("invalidEndpointList.json"));
    }

    @Test
    public void readEndpointList_invalidAndValidEndpointList_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readEndpointList("invalidAndValidEndpointList.json"));
    }

    @Test
    public void readAndSaveEndpointList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEndpointList.json");
        EndpointList original = TypicalEndpoints.getTypicalEndpointList();
        JsonEndpointListStorage jsonEndpointListStorage = new JsonEndpointListStorage(filePath);

        // Save in new file and read back
        jsonEndpointListStorage.saveEndpointList(original, filePath);
        ReadOnlyEndpointList readBack = jsonEndpointListStorage.readEndpointList(filePath).get();
        assertEquals(original, new EndpointList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEndpoint(TypicalEndpoints.HOON);
        original.removeEndpoint(TypicalEndpoints.ALICE);
        jsonEndpointListStorage.saveEndpointList(original, filePath);
        readBack = jsonEndpointListStorage.readEndpointList(filePath).get();
        assertEquals(original, new EndpointList(readBack));

        // Save and read without specifying file path
        original.addEndpoint(TypicalEndpoints.IDA);
        jsonEndpointListStorage.saveEndpointList(original); // file path not specified
        readBack = jsonEndpointListStorage.readEndpointList().get(); // file path not specified
        assertEquals(original, new EndpointList(readBack));

    }

    @Test
    public void saveEndpointList_nullEndpointList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveEndpointList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code endpointList} at the specified {@code filePath}.
     */
    private void saveEndpointList(ReadOnlyEndpointList endpointList, String filePath) {
        try {
            new JsonEndpointListStorage(Paths.get(filePath))
                    .saveEndpointList(endpointList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEndpointList_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveEndpointList(new EndpointList(), null));
    }
}
