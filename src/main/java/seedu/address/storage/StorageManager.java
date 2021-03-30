package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHeyMatez;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HeyMatez data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HeyMatezStorage heyMatezStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HeyMatezStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HeyMatezStorage heyMatezStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.heyMatezStorage = heyMatezStorage;
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


    // ================ HeyMatez methods ==============================

    @Override
    public Path getHeyMatezFilePath() {
        return heyMatezStorage.getHeyMatezFilePath();
    }

    @Override
    public Optional<ReadOnlyHeyMatez> readHeyMatez() throws DataConversionException, IOException {
        return readHeyMatez(heyMatezStorage.getHeyMatezFilePath());
    }

    @Override
    public Optional<ReadOnlyHeyMatez> readHeyMatez(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return heyMatezStorage.readHeyMatez(filePath);
    }

    @Override
    public void saveHeyMatez(ReadOnlyHeyMatez heyMatez) throws IOException {
        saveHeyMatez(heyMatez, heyMatezStorage.getHeyMatezFilePath());
    }

    @Override
    public void saveHeyMatez(ReadOnlyHeyMatez heyMatez, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        heyMatezStorage.saveHeyMatez(heyMatez, filePath);
    }
}
