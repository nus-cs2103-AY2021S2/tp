package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachingAssistant;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TeachingAssistant data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TeachingAssistantStorage teachingAssistantStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TeachingAssistantStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TeachingAssistantStorage teachingAssistantStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.teachingAssistantStorage = teachingAssistantStorage;
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


    // ================ TeachingAssistant methods ==============================

    @Override
    public Path getTeachingAssistantFilePath() {
        return teachingAssistantStorage.getTeachingAssistantFilePath();
    }

    @Override
    public Optional<ReadOnlyTeachingAssistant> readTeachingAssistant() throws DataConversionException, IOException {
        return readTeachingAssistant(teachingAssistantStorage.getTeachingAssistantFilePath());
    }

    @Override
    public Optional<ReadOnlyTeachingAssistant> readTeachingAssistant(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return teachingAssistantStorage.readTeachingAssistant(filePath);
    }

    @Override
    public void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant) throws IOException {
        saveTeachingAssistant(teachingAssistant, teachingAssistantStorage.getTeachingAssistantFilePath());
    }

    @Override
    public void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        teachingAssistantStorage.saveTeachingAssistant(teachingAssistant, filePath);
    }

}
