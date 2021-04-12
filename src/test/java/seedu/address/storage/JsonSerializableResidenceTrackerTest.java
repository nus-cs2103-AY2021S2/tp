package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableResidenceTrackerTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableResidenceTrackerTest");
    private static final Path TYPICAL_RESIDENCE_FILE = TEST_DATA_FOLDER
            .resolve("typicalResidencesResidenceTracker.json");
    private static final Path INVALID_RESIDENCE_FILE = TEST_DATA_FOLDER
            .resolve("invalidResidenceResidenceTracker.json");
    private static final Path DUPLICATE_RESIDENCE_FILE = TEST_DATA_FOLDER
            .resolve("duplicateResidenceResidenceTracker.json");

    @Test
    public void toModelType_invalidResidenceFile_throwsIllegalValueException() throws Exception {
        JsonSerializableResidenceTracker dataFromFile = JsonUtil.readJsonFile(INVALID_RESIDENCE_FILE,
                JsonSerializableResidenceTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateResidences_throwsIllegalValueException() throws Exception {
        JsonSerializableResidenceTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_RESIDENCE_FILE,
                JsonSerializableResidenceTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResidenceTracker.MESSAGE_DUPLICATE_RESIDENCE,
                dataFromFile::toModelType);
    }

}
