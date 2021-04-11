package seedu.flashback.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.flashback.commons.core.LogsCenter;
import seedu.flashback.commons.exceptions.DataConversionException;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.ReadOnlyUserPrefs;
import seedu.flashback.model.UserPrefs;

/**
 * Manages storage of FlashBack data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlashBackStorage flashBackStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashBackStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashBackStorage flashBackStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.flashBackStorage = flashBackStorage;
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


    // ================ FlashBack methods ==============================

    @Override
    public Path getFlashBackFilePath() {
        return flashBackStorage.getFlashBackFilePath();
    }

    @Override
    public Optional<ReadOnlyFlashBack> readFlashBack() throws DataConversionException, IOException {
        return readFlashBack(flashBackStorage.getFlashBackFilePath());
    }

    @Override
    public Optional<ReadOnlyFlashBack> readFlashBack(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashBackStorage.readFlashBack(filePath);
    }

    @Override
    public void saveFlashBack(ReadOnlyFlashBack flashBack) throws IOException {
        saveFlashBack(flashBack, flashBackStorage.getFlashBackFilePath());
    }

    @Override
    public void saveFlashBack(ReadOnlyFlashBack flashBack, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashBackStorage.saveFlashBack(flashBack, filePath);
    }

}
