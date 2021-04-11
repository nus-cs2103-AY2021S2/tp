package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TeachingAssistant;
import seedu.address.testutil.TypicalTeachingAssistant;

public class JsonSerializableTeachingAssistantTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableTeachingAssistantTest");
    private static final Path INVALID_CONTACT_FILE = TEST_DATA_FOLDER.resolve("invalidContactTeachingAssistant.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve(
                                                                    "duplicateContactTeachingAssistant.json");
    private static final Path TYPICAL_TEACHING_ASSISTANT_FILE = TEST_DATA_FOLDER.resolve(
                                                                    "typicalTeachingAssistant.json");
    private static final Path OVERLAPPING_ENTRY_FILE = TEST_DATA_FOLDER.resolve(
                                                                    "overlappingEntryTeachingAssistant.json");
    @Test
    public void toModelType_typicalTeachingAssistantFile_success() throws Exception {
        JsonSerializableTeachingAssistant dataFromFile = JsonUtil.readJsonFile(TYPICAL_TEACHING_ASSISTANT_FILE,
                JsonSerializableTeachingAssistant.class).get();
        TeachingAssistant teachingAssistantFromFile = dataFromFile.toModelType();
        TeachingAssistant typicalTeachingAssistant = TypicalTeachingAssistant.getTypicalTeachingAssistant();
        assertEquals(teachingAssistantFromFile, typicalTeachingAssistant);
    }

    @Test
    public void toModelType_overlappingEntries_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachingAssistant dataFromFile = JsonUtil.readJsonFile(OVERLAPPING_ENTRY_FILE,
                JsonSerializableTeachingAssistant.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachingAssistant.MESSAGE_OVERLAPPING_ENTRY,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachingAssistant dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
                JsonSerializableTeachingAssistant.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachingAssistant.MESSAGE_DUPLICATE_CONTACT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidContact_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachingAssistant dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACT_FILE,
                JsonSerializableTeachingAssistant.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
