package seedu.taskify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taskify.commons.core.LogsCenter;
import seedu.taskify.commons.exceptions.DataConversionException;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.ReadOnlyUserPrefs;
import seedu.taskify.model.UserPrefs;

/**
 * Manages storage of TaskifyParser data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskifyStorage taskifyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskifyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskifyStorage taskifyStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.taskifyStorage = taskifyStorage;
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


    // ================ TaskifyParser methods ==============================

    @Override
    public Path getTaskifyFilePath() {
        return taskifyStorage.getTaskifyFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskify> readTaskifyData() throws DataConversionException, IOException {
        return readTaskifyData(taskifyStorage.getTaskifyFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskify> readTaskifyData(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskifyStorage.readTaskifyData(filePath);
    }

    @Override
    public void saveTaskifyData(ReadOnlyTaskify taskify) throws IOException {
        saveTaskifyData(taskify, taskifyStorage.getTaskifyFilePath());
    }

    @Override
    public void saveTaskifyData(ReadOnlyTaskify taskify, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskifyStorage.saveTaskifyData(taskify, filePath);
    }

}
