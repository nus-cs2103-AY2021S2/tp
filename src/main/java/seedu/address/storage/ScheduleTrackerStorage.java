package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;

/**
 * Represents a storage for {@link seedu.address.model.schedule.ScheduleTracker}
 */
public interface ScheduleTrackerStorage {

    /**
     * @return File path of Schedule Tracker data file
     */
    Path getScheduleTrackerFilePath();

    /**
     * Returns Schedule Tracker as a {@link seedu.address.model.schedule.ReadOnlyScheduleTracker}
     * @return {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyScheduleTracker> readScheduleTracker() throws DataConversionException,
            IOException;

    /**
     * @param filePath File path of Schedule Tracker
     * @return {@code Optional.empty()} if filePath is an empty Schedule Tracker
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyScheduleTracker> readScheduleTracker(Path filePath) throws DataConversionException,
            IOException;


    /**
     * Saves the given {@link ReadOnlyScheduleTracker} to the storage.
     * @param scheduleTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker) throws IOException;

    /**
     * @see #saveScheduleTracker(ReadOnlyScheduleTracker)
     */
    void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker, Path filePath) throws IOException;
}
