package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Wardrobe data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WardrobeStorage wardrobeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WardrobeStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WardrobeStorage wardrobeStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.wardrobeStorage = wardrobeStorage;
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


    // ================ Wardrobe methods ==============================

    @Override
    public Path getWardrobeFilePath() {
        return wardrobeStorage.getWardrobeFilePath();
    }

    @Override
    public Optional<ReadOnlyWardrobe> readWardrobe() throws DataConversionException, IOException {
        return readWardrobe(wardrobeStorage.getWardrobeFilePath());
    }

    @Override
    public Optional<ReadOnlyWardrobe> readWardrobe(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return wardrobeStorage.readWardrobe(filePath);
    }

    @Override
    public void saveWardrobe(ReadOnlyWardrobe wardrobe) throws IOException {
        saveWardrobe(wardrobe, wardrobeStorage.getWardrobeFilePath());
    }

    @Override
    public void saveWardrobe(ReadOnlyWardrobe wardrobe, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        wardrobeStorage.saveWardrobe(wardrobe, filePath);
    }

}
