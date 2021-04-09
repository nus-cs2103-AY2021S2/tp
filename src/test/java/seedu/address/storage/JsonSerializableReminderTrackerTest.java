package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.reminder.ReminderTracker;
import seedu.address.testutil.TypicalReminders;

public class JsonSerializableReminderTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableReminderTrackerTest");
    private static final Path TYPICAL_REMINDER_FILE = TEST_DATA_FOLDER.resolve("typicalReminderTracker.json");
    private static final Path INVALID_REMINDER_FILE = TEST_DATA_FOLDER.resolve("invalidReminderTracker.json");
    private static final Path DUPLICATE_REMINDER_FILE = TEST_DATA_FOLDER.resolve("duplicateReminderTracker.json");

    @Test
    public void toModelType_typicalReminderFile_success() throws Exception {
        JsonSerializableReminderTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_REMINDER_FILE,
                JsonSerializableReminderTracker.class).get();
        ReminderTracker scheduleTrackerFromFile = dataFromFile.toModelType();
        ReminderTracker typicalReminderTracker = TypicalReminders.getTypicalReminderTracker();
        assertEquals(scheduleTrackerFromFile, typicalReminderTracker);
    }

    @Test
    public void toModelType_invalidReminderFile_throwsIllegalValueException() throws Exception {
        JsonSerializableReminderTracker dataFromFile = JsonUtil.readJsonFile(INVALID_REMINDER_FILE,
                JsonSerializableReminderTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateReminder_throwsIllegalValueException() throws Exception {
        JsonSerializableReminderTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_REMINDER_FILE,
                JsonSerializableReminderTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableReminderTracker.MESSAGE_DUPLICATE_REMINDER,
                dataFromFile::toModelType);
    }
}
