package seedu.taskify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taskify.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.taskify.commons.exceptions.IllegalValueException;
import seedu.taskify.commons.util.JsonUtil;
import seedu.taskify.model.Taskify;
import seedu.taskify.testutil.TypicalTasks;

public class JsonSerializableTaskifyParserTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskifyParserTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskify.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskify.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTaskify.json");


    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskify dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTaskify.class).get();
        Taskify taskifyFromFile = dataFromFile.toModelType();
        Taskify typicalTasksTaskify = TypicalTasks.getTypicalAddressBook();
        assertEquals(taskifyFromFile, typicalTasksTaskify);
    }


    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskify dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskify.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskify dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTaskify.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskify.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
