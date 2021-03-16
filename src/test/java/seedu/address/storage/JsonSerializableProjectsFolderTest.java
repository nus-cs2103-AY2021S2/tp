package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ProjectsFolder;
import seedu.address.testutil.TypicalProjects;

public class JsonSerializableProjectsFolderTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableProjectsFolderTest");
    private static final Path TYPICAL_PROJECTS_FILE = TEST_DATA_FOLDER.resolve("typicalProjectsProjectsFolder.json");
    private static final Path INVALID_PROJECT_FILE = TEST_DATA_FOLDER.resolve("invalidProjectProjectsFolder.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectProjectsFolder.json");

    @Test
    public void toModelType_typicalProjectsFile_success() throws Exception {
        JsonSerializableProjectsFolder dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROJECTS_FILE,
                JsonSerializableProjectsFolder.class).get();
        ProjectsFolder projectsFolderFromFile = dataFromFile.toModelType();
        ProjectsFolder typicalProjectsProjectsFolder = TypicalProjects.getTypicalProjectsFolder();
        assertEquals(projectsFolderFromFile, typicalProjectsProjectsFolder);
    }

    @Test
    public void toModelType_invalidProjectFile_throwsIllegalValueException() throws Exception {
        JsonSerializableProjectsFolder dataFromFile = JsonUtil.readJsonFile(INVALID_PROJECT_FILE,
                JsonSerializableProjectsFolder.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProjects_throwsIllegalValueException() throws Exception {
        JsonSerializableProjectsFolder dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
                JsonSerializableProjectsFolder.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableProjectsFolder.MESSAGE_DUPLICATE_PROJECTS,
                dataFromFile::toModelType);
    }

}
