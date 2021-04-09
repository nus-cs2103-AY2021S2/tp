package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_END_DATETIME_BEFORE_START_DATETIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.Sochedule;
import seedu.address.testutil.TypicalEvents;
import seedu.address.testutil.TypicalTasks;

public class JsonSerializableSocheduleTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSocheduleTest");

    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskSochedule.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskSochedule.json");
    private static final Path TYPICAL_TASKS = TEST_DATA_FOLDER.resolve("../sandbox/typicalTaskSochedule.json");

    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventSochedule.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventSochedule.json");
    private static final Path TYPICAL_EVENTS = TEST_DATA_FOLDER.resolve("../sandbox/typicalEventSochedule.json");
    private static final Path INVALID_EVENT_SCHEDULING_FILE =
            TEST_DATA_FOLDER.resolve("invalidEventSchedulingSochedule.json");

    @Test
    public void toModelType_typicalTaskSocheduleFile_success() throws Exception {
        //file setup
        Sochedule typicalSochedule = TypicalTasks.getTypicalSochedule();
        new JsonSocheduleStorageStub().saveSochedule(typicalSochedule, TYPICAL_TASKS);

        //read file
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS,
                JsonSerializableSochedule.class).get();
        Sochedule socheduleFromFile = dataFromFile.toModelType();
        assertEquals(socheduleFromFile, typicalSochedule);
    }

    @Test
    public void toModelType_typicalEventSocheduleFile_success() throws Exception {
        //file setup
        Sochedule typicalSochedule = TypicalEvents.getTypicalSochedule();
        new JsonSocheduleStorageStub().saveSochedule(typicalSochedule, TYPICAL_EVENTS);

        //read file
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS,
                JsonSerializableSochedule.class).get();
        Sochedule socheduleFromFile = dataFromFile.toModelType();
        assertEquals(socheduleFromFile, typicalSochedule);
    }

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
    public void toModelType_improperSchedulingEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableSochedule dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_SCHEDULING_FILE,
                JsonSerializableSochedule.class).get();
        assertThrows(IllegalValueException.class, MESSAGE_END_DATETIME_BEFORE_START_DATETIME,
                dataFromFile::toModelType);
    }
}

class JsonSocheduleStorageStub implements SocheduleStorage {
    @Override
    public Path getSocheduleFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlySochedule> readSochedule() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlySochedule> readSochedule(Path filePath) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveSochedule(ReadOnlySochedule sochedule) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveSochedule(ReadOnlySochedule sochedule, Path filePath) throws IOException {
        requireNonNull(sochedule);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSochedule(sochedule), filePath);
    }

}
