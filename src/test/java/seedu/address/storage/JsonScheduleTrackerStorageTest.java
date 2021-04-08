package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.ENGLISH_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.LITERATURE_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.ScheduleTracker;

public class JsonScheduleTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonScheduleTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readScheduleTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readScheduleTracker(null));
    }

    private java.util.Optional<ReadOnlyScheduleTracker> readScheduleTracker(String filePath) throws Exception {
        return new JsonScheduleTrackerStorage(Paths.get(filePath)).readScheduleTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readScheduleTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readScheduleTracker("notJsonFormatScheduleTracker.json"));
    }

    @Test
    public void readScheduleTracker_invalidTutorScheduleTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readScheduleTracker("invalidScheduleTracker.json"));
    }

    @Test
    public void readScheduleTracker_invalidAndValidTutorScheduleTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readScheduleTracker("invalidAndValidScheduleTracker.json"));
    }

    @Test
    public void readAndSaveScheduleTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempScheduleTracker.json");
        ScheduleTracker original = getTypicalScheduleTracker();
        JsonScheduleTrackerStorage jsonScheduleTrackerStorage = new JsonScheduleTrackerStorage(filePath);

        // Save in new file and read back
        jsonScheduleTrackerStorage.saveScheduleTracker(original, filePath);
        ReadOnlyScheduleTracker readBack = jsonScheduleTrackerStorage.readScheduleTracker(filePath).get();
        assertEquals(original, new ScheduleTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addSchedule(ENGLISH_HOMEWORK_SCHEDULE);
        original.removeSchedule(MATHS_HOMEWORK_SCHEDULE);
        jsonScheduleTrackerStorage.saveScheduleTracker(original, filePath);
        readBack = jsonScheduleTrackerStorage.readScheduleTracker(filePath).get();
        assertEquals(original, new ScheduleTracker(readBack));

        // Save and read without specifying file path
        original.addSchedule(LITERATURE_HOMEWORK_SCHEDULE);
        jsonScheduleTrackerStorage.saveScheduleTracker(original); // file path not specified
        readBack = jsonScheduleTrackerStorage.readScheduleTracker().get(); // file path not specified
        assertEquals(original, new ScheduleTracker(readBack));
    }

    @Test
    public void saveScheduleTracker_nullScheduleTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveScheduleTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code scheduleTracker} at the specified {@code filePath}.
     */
    private void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker, String filePath) {
        try {
            new JsonScheduleTrackerStorage(Paths.get(filePath))
                    .saveScheduleTracker(scheduleTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveScheduleTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveScheduleTracker(new ScheduleTracker(), null));
    }
}
