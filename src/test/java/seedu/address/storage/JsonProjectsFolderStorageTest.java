package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ProjectsFolder;
import seedu.address.model.ReadOnlyProjectsFolder;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TypicalProjects;

public class JsonProjectsFolderStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonProjectsFolderStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProjectsFolder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProjectsFolder(null));
    }

    private java.util.Optional<ReadOnlyProjectsFolder> readProjectsFolder(String filePath) throws Exception {
        return new JsonProjectsFolderStorage(Paths.get(filePath))
                .readProjectsFolder(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProjectsFolder("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readProjectsFolder("notJsonFormatProjectsFolder.json"));
    }

    @Test
    public void readProjectsFolder_invalidProjectProjectsFolder_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readProjectsFolder("invalidProjectProjectsFolder.json"));
    }

    @Test
    public void readProjectsFolder_invalidAndValidProjectProjectsFolder_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readProjectsFolder("invalidAndValidProjectProjectsFolder.json"));
    }

    @Test
    public void readAndSaveProjectsFolder_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProjectsFolder.json");
        ProjectsFolder original = getTypicalProjectsFolder();
        JsonProjectsFolderStorage jsonProjectsFolderStorage = new JsonProjectsFolderStorage(filePath);

        // Save in new file and read back
        jsonProjectsFolderStorage.saveProjectsFolder(original, filePath);
        ReadOnlyProjectsFolder readBack = jsonProjectsFolderStorage.readProjectsFolder(filePath).get();
        assertEquals(original, new ProjectsFolder(readBack));

        // Test Project
        Project project = TypicalProjects.getTypicalProjects().get(0);

        // Modify data, overwrite exiting file, and read back
        original.addProject(new ProjectBuilder().withName("Test").build());
        original.removeProject(project);
        jsonProjectsFolderStorage.saveProjectsFolder(original, filePath);
        readBack = jsonProjectsFolderStorage.readProjectsFolder(filePath).get();
        assertEquals(original, new ProjectsFolder(readBack));

        // Save and read without specifying file path
        original.addProject(project);
        jsonProjectsFolderStorage.saveProjectsFolder(original); // file path not specified
        readBack = jsonProjectsFolderStorage.readProjectsFolder().get(); // file path not specified
        assertEquals(original, new ProjectsFolder(readBack));

    }

    @Test
    public void saveProjectsFolder_nullProjectsFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProjectsFolder(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveProjectsFolder(ReadOnlyProjectsFolder projectsFolder, String filePath) {
        try {
            new JsonProjectsFolderStorage(Paths.get(filePath))
                    .saveProjectsFolder(projectsFolder, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProjectsFolder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProjectsFolder(new ProjectsFolder(), null));
    }
}
