package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TaskTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskTrackerStorage taskTrackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskTrackerStorage taskTrackerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.taskTrackerStorage = taskTrackerStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TaskTracker methods ==============================

    @Override
    public Path getTaskTrackerFilePath() {
        return taskTrackerStorage.getTaskTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskTracker> readTaskTracker() throws DataConversionException, IOException {
        return readTaskTracker(taskTrackerStorage.getTaskTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskTracker> readTaskTracker(Path filePath) throws DataConversionException, IOException {

        logger.fine("Attempting to read data from file: " + filePath);
        return taskTrackerStorage.readTaskTracker(filePath);
    }

    @Override
    public void saveTaskTracker(ReadOnlyTaskTracker taskTracker) throws IOException {
        saveTaskTracker(taskTracker, taskTrackerStorage.getTaskTrackerFilePath());
    }

    @Override
    public void saveTaskTracker(ReadOnlyTaskTracker taskTracker, Path filePath) throws IOException {

        logger.fine("Attempting to write to data file: " + filePath);
        taskTrackerStorage.saveTaskTracker(taskTracker, filePath);
    }

}
