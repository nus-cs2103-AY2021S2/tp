package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of RemindMe data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RemindMeStorage remindMeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code RemindMeStorage} and {@code
     * UserPrefStorage}.
     */
    public StorageManager(RemindMeStorage remindMeStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.remindMeStorage = remindMeStorage;
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


    // ================ RemindMe methods ==============================
    @Override
    public Path getRemindMeFilePath() {
        return remindMeStorage.getRemindMeFilePath();
    }

    @Override
    public Optional<ReadOnlyRemindMe> readRemindMe() throws DataConversionException, IOException {
        return readRemindMe(remindMeStorage.getRemindMeFilePath());
    }

    @Override
    public Optional<ReadOnlyRemindMe> readRemindMe(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return remindMeStorage.readRemindMe(filePath);
    }

    @Override
    public void saveRemindMe(ReadOnlyRemindMe remindMeApp) throws IOException {
        saveRemindMe(remindMeApp, remindMeStorage.getRemindMeFilePath());
    }

    @Override
    public void saveRemindMe(ReadOnlyRemindMe remindMeApp, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        remindMeStorage.saveRemindMe(remindMeApp, filePath);
    }

}
