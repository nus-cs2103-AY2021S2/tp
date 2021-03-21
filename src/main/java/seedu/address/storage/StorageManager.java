package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ResidenceTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ResidenceTrackerStorage residenceTrackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ResidenceTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ResidenceTrackerStorage residenceTrackerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.residenceTrackerStorage = residenceTrackerStorage;
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


    // ================ ResidenceTracker methods ==============================

    @Override
    public Path getResidenceTrackerFilePath() {
        return residenceTrackerStorage.getResidenceTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyResidenceTracker> readResidenceTracker() throws DataConversionException, IOException {
        return readResidenceTracker(residenceTrackerStorage.getResidenceTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyResidenceTracker> readResidenceTracker(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return residenceTrackerStorage.readResidenceTracker(filePath);
    }

    @Override
    public void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker) throws IOException {
        saveResidenceTracker(residenceTracker, residenceTrackerStorage.getResidenceTrackerFilePath());
    }

    @Override
    public void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        residenceTrackerStorage.saveResidenceTracker(residenceTracker, filePath);
    }

}
