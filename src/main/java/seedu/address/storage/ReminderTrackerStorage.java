package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.reminder.ReadOnlyReminderTracker;

/**
 * Represents a storage for {@link seedu.address.model.reminder.ReminderTracker}
 */
public interface ReminderTrackerStorage {

    /**
     * @return File path of Reminder Tracker data file
     */
    Path getReminderTrackerFilePath();

    /**
     * Returns Reminder Tracker as a {@link ReadOnlyReminderTracker}
     *
     * @return {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyReminderTracker> readReminderTracker() throws DataConversionException,
            IOException;

    /**
     * @param filePath File path of Reminder Tracker
     * @return {@code Optional.empty()} if filePath is an empty Reminder Tracker
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyReminderTracker> readReminderTracker(Path filePath) throws DataConversionException,
            IOException;


    /**
     * Saves the given {@link ReadOnlyReminderTracker} to the storage.
     *
     * @param reminderTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveReminderTracker(ReadOnlyReminderTracker reminderTracker) throws IOException;

    /**
     * @see #saveReminderTracker(ReadOnlyReminderTracker)
     */
    void saveReminderTracker(ReadOnlyReminderTracker reminderTracker, Path filePath) throws IOException;
}
