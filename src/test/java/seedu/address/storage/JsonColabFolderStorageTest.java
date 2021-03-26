package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalProjects.getCS2103TProject;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ColabFolder;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.testutil.ProjectBuilder;

public class JsonColabFolderStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonColabFolderStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readColabFolder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readColabFolder(null));
    }

    private java.util.Optional<ReadOnlyColabFolder> readColabFolder(String filePath) throws Exception {
        return new JsonColabFolderStorage(Paths.get(filePath)).readColabFolder(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readColabFolder("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readColabFolder("notJsonFormatColabFolder.json"));
    }

    @Test
    public void readColabFolder_invalidPersonColabFolder_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readColabFolder("invalidPersonColabFolder.json"));
    }

    @Test
    public void readColabFolder_invalidAndValidPersonColabFolder_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readColabFolder("invalidAndValidPersonColabFolder.json"));
    }

    @Test
    public void readColabFolder_invalidProjectColabFolder_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readColabFolder("invalidProjectColabFolder.json"));
    }

    @Test
    public void readColabFolder_invalidAndValidProjectColabFolder_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readColabFolder("invalidAndValidProjectColabFolder.json"));
    }

    @Test
    public void readAndSaveColabFolder_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempColabFolder.json");
        ColabFolder original = getTypicalColabFolder();
        JsonColabFolderStorage jsonColabFolderStorage = new JsonColabFolderStorage(filePath);

        // Save in new file and read back
        jsonColabFolderStorage.saveColabFolder(original, filePath);
        ReadOnlyColabFolder readBack = jsonColabFolderStorage.readColabFolder(filePath).get();
        assertEquals(original, new ColabFolder(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        original.removeProject(getCS2103TProject());
        original.addProject(new ProjectBuilder().build());
        jsonColabFolderStorage.saveColabFolder(original, filePath);
        readBack = jsonColabFolderStorage.readColabFolder(filePath).get();
        assertEquals(original, new ColabFolder(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        original.addProject(new ProjectBuilder().withName("Test Name").build());
        jsonColabFolderStorage.saveColabFolder(original); // file path not specified
        readBack = jsonColabFolderStorage.readColabFolder().get(); // file path not specified
        assertEquals(original, new ColabFolder(readBack));

    }

    @Test
    public void saveColabFolder_nullColabFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveColabFolder(null, "SomeFile.json"));
    }

    /**
     * Saves {@code colabFolder} at the specified {@code filePath}.
     */
    private void saveColabFolder(ReadOnlyColabFolder colabFolder, String filePath) {
        try {
            new JsonColabFolderStorage(Paths.get(filePath))
                    .saveColabFolder(colabFolder, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveColabFolder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveColabFolder(new ColabFolder(), null));
    }
}
