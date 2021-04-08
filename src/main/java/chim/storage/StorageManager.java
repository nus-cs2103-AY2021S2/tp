package chim.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import chim.commons.core.LogsCenter;
import chim.commons.exceptions.DataConversionException;
import chim.model.ReadOnlyChim;
import chim.model.ReadOnlyUserPrefs;
import chim.model.UserPrefs;

/**
 * Manages storage of CHIM data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ChimStorage chimStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ChimStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ChimStorage chimStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.chimStorage = chimStorage;
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


    // ================ Chim methods ==============================

    @Override
    public Path getChimFilePath() {
        return chimStorage.getChimFilePath();
    }

    @Override
    public Optional<ReadOnlyChim> readChim() throws DataConversionException, IOException {
        return readChim(chimStorage.getChimFilePath());
    }

    @Override
    public Optional<ReadOnlyChim> readChim(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return chimStorage.readChim(filePath);
    }

    @Override
    public void saveChim(ReadOnlyChim chim) throws IOException {
        saveChim(chim, chimStorage.getChimFilePath());
    }

    @Override
    public void saveChim(ReadOnlyChim chim, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        chimStorage.saveChim(chim, filePath);
    }

}
