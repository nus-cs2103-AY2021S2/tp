package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReminders.ENGLISH_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.LITERATURE_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.ReminderTracker;

public class JsonReminderTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonReminderTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readReminderTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readReminderTracker(null));
    }

    private java.util.Optional<ReadOnlyReminderTracker> readReminderTracker(String filePath) throws Exception {
        return new JsonReminderTrackerStorage(Paths.get(filePath))
                .readReminderTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readReminderTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readReminderTracker("notJsonFormatReminderTracker.json"));
    }

    @Test
    public void readReminderTracker_invalidTutorReminderTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readReminderTracker("invalidReminderTracker.json"));
    }

    @Test
    public void readReminderTracker_invalidAndValidTutorReminderTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readReminderTracker("invalidAndValidReminderTracker.json"));
    }

    @Test
    public void readAndSaveReminderTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempReminderTracker.json");
        ReminderTracker original = getTypicalReminderTracker();
        JsonReminderTrackerStorage jsonReminderTrackerStorage = new JsonReminderTrackerStorage(filePath);

        // Save in new file and read back
        jsonReminderTrackerStorage.saveReminderTracker(original, filePath);
        ReadOnlyReminderTracker readBack = jsonReminderTrackerStorage.readReminderTracker(filePath).get();
        assertEquals(original, new ReminderTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addReminder(ENGLISH_TUITION_PAYMENT_REMINDER);
        original.removeReminder(MATHS_TUITION_PAYMENT_REMINDER);
        jsonReminderTrackerStorage.saveReminderTracker(original, filePath);
        readBack = jsonReminderTrackerStorage.readReminderTracker(filePath).get();
        assertEquals(original, new ReminderTracker(readBack));

        // Save and read without specifying file path
        original.addReminder(LITERATURE_TUITION_PAYMENT_REMINDER);
        jsonReminderTrackerStorage.saveReminderTracker(original); // file path not specified
        readBack = jsonReminderTrackerStorage.readReminderTracker().get(); // file path not specified
        assertEquals(original, new ReminderTracker(readBack));
    }

    @Test
    public void saveReminderTracker_nullReminderTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveReminderTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code reminderTracker} at the specified {@code filePath}.
     */
    private void saveReminderTracker(ReadOnlyReminderTracker reminderTracker, String filePath) {
        try {
            new JsonReminderTrackerStorage(Paths.get(filePath))
                    .saveReminderTracker(reminderTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveReminderTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveReminderTracker(new ReminderTracker(), null));
    }
}
