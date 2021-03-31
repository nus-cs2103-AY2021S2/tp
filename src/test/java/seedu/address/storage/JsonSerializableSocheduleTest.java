package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableSocheduleTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSocheduleTest");
    private static final Path TYPICAL_SOCHEDULE = TEST_DATA_FOLDER.resolve("typicalSochedule.json");

    private static final Path ONLY_TASK_FILE = TEST_DATA_FOLDER.resolve("noEventTypicalSochedule.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskSochedule.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskSochedule.json");

    private static final Path ONLY_EVENT_FILE = TEST_DATA_FOLDER.resolve("noTaskTypicalSochedule.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventSochedule.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventSochedule.json");
    private static final Path INVALID_EVENT_SCHEDULING_FILE =
            TEST_DATA_FOLDER.resolve("invalidEventSchedulingSochedule.json");

    /* PENDING CREATION OF TypicalSochedule, TypicalEvents
    @Test
    public void toModelType_typicalSocheduleFile_success() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_SOCHEDULE,
                JsonSerializableSochedule.class).get();
        Sochedule socheduleFromFile = dataFromFile.toModelType();
        Sochedule typicalSochedule = TypicalSochedule.getTypicalSochedule();
        assertEquals(socheduleFromFile, typicalSochedule);
    }

    MISSING:
    toModelType_typicalTaskSocheduleFile_success()
    toModelType_typicalEventSocheduleFile_success()
    -- add get TaskOnlySochedule, EventOnlySochedule under TypicalSochedule
    */

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableSochedule.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableSochedule.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableSochedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSochedule.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableSochedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSochedule.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }

    @Test
    @Disabled
    // PENDING BUG SQUASH
    public void toModelType_improperSchedulingEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_SCHEDULING_FILE,
                JsonSerializableSochedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSochedule.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
