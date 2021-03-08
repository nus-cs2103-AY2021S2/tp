package seedu.storemando.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.ReadOnlyUserPrefs;
import seedu.storemando.model.UserPrefs;

/**
 * Manages storage of StoreMando data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final StoreMandoStorage storeMandoStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StoreMandoStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StoreMandoStorage storeMandoStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.storeMandoStorage = storeMandoStorage;
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


    // ================ StoreMando methods ==============================

    @Override
    public Path getStoreMandoFilePath() {
        return storeMandoStorage.getStoreMandoFilePath();
    }

    @Override
    public Optional<ReadOnlyStoreMando> readStoreMando() throws DataConversionException, IOException {
        return readStoreMando(storeMandoStorage.getStoreMandoFilePath());
    }

    @Override
    public Optional<ReadOnlyStoreMando> readStoreMando(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return storeMandoStorage.readStoreMando(filePath);
    }

    @Override
    public void saveStoreMando(ReadOnlyStoreMando storeMando) throws IOException {
        saveStoreMando(storeMando, storeMandoStorage.getStoreMandoFilePath());
    }

    @Override
    public void saveStoreMando(ReadOnlyStoreMando storeMando, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        storeMandoStorage.saveStoreMando(storeMando, filePath);
    }

}
