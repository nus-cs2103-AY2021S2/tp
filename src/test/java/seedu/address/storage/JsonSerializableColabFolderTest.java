package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ColabFolder;

public class JsonSerializableColabFolderTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableColabFolderTest");
    private static final Path TYPICAL_DATA_FILE = TEST_DATA_FOLDER.resolve("typicalColabFolder.json");
    private static final Path INVALID_CONTACT_FILE = TEST_DATA_FOLDER.resolve("invalidContactColabFolder.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve("duplicateContactColabFolder.json");
    private static final Path INVALID_PROJECT_FILE = TEST_DATA_FOLDER.resolve("invalidProjectColabFolder.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectColabFolder.json");

    @Test
    public void toModelType_typicalDataFile_success() throws Exception {
        JsonSerializableColabFolder dataFromFile = JsonUtil.readJsonFile(TYPICAL_DATA_FILE,
                JsonSerializableColabFolder.class).get();
        ColabFolder colabFolderFromFile = dataFromFile.toModelType();
        ColabFolder typicalContactsColabFolder = getTypicalColabFolder();
        assertEquals(colabFolderFromFile, typicalContactsColabFolder);
    }

    @Test
    public void toModelType_invalidContactFile_throwsIllegalValueException() throws Exception {
        JsonSerializableColabFolder dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACT_FILE,
                JsonSerializableColabFolder.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableColabFolder dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
                JsonSerializableColabFolder.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableColabFolder.MESSAGE_DUPLICATE_CONTACTS,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidProject_throwsIllegalValueException() throws Exception {
        JsonSerializableColabFolder dataFromFile = JsonUtil.readJsonFile(INVALID_PROJECT_FILE,
                JsonSerializableColabFolder.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProjects_throwsIllegalValueException() throws Exception {
        JsonSerializableColabFolder dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
                JsonSerializableColabFolder.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableColabFolder.MESSAGE_DUPLICATE_PROJECTS,
                dataFromFile::toModelType);
    }

}
