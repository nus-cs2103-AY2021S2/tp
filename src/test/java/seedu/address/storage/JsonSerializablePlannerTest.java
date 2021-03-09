package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Planner;
import seedu.address.testutil.TypicalTasks;

public class JsonSerializablePlannerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePlannerTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksPlanner.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskPlanner.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskPlanner.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializablePlanner.class).get();
        Planner plannerFromFile = dataFromFile.toModelType();
        Planner typicalTasksPlanner = TypicalTasks.getTypicalPlanner();
        assertEquals(plannerFromFile, typicalTasksPlanner);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializablePlanner.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializablePlanner.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePlanner.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
