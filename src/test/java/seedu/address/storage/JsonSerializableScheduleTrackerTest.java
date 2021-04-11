package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.testutil.TypicalSchedules;

public class JsonSerializableScheduleTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableScheduleTrackerTest");
    private static final Path TYPICAL_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("typicalScheduleTracker.json");
    private static final Path INVALID_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("invalidScheduleTracker.json");
    private static final Path DUPLICATE_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("duplicateScheduleTracker.json");
    private static final Path MISMATCH_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("mismatchDateScheduleTracker.json");
    private static final Path INVALID_TIME_RANGE_SCHEDULE_FILE =
            TEST_DATA_FOLDER.resolve("invalidTimeRangeScheduleTracker.json");

    @Test
    public void toModelType_typicalScheduleFile_success() throws Exception {
        JsonSerializableScheduleTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_SCHEDULE_FILE,
                JsonSerializableScheduleTracker.class).get();
        ScheduleTracker scheduleTrackerFromFile = dataFromFile.toModelType();
        ScheduleTracker typicalScheduleTracker = TypicalSchedules.getTypicalScheduleTracker();
        assertEquals(scheduleTrackerFromFile, typicalScheduleTracker);
    }

    @Test
    public void toModelType_invalidScheduleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduleTracker dataFromFile = JsonUtil.readJsonFile(INVALID_SCHEDULE_FILE,
                JsonSerializableScheduleTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSchedule_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduleTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SCHEDULE_FILE,
                JsonSerializableScheduleTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableScheduleTracker.MESSAGE_DUPLICATE_SCHEDULE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_mismatchScheduleDate_throwsIllegalValueException() throws Exception {
        JsonSerializableScheduleTracker dataFromFile = JsonUtil.readJsonFile(MISMATCH_SCHEDULE_FILE,
                JsonSerializableScheduleTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableScheduleTracker.MESSAGE_MISMATCH_DATE,
                dataFromFile::toModelType);
    }
}
