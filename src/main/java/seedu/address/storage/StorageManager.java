package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ColabFolder data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ColabFolderStorage colabFolderStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ColabFolderStorage}, {@code ProjectsFolderStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(ColabFolderStorage colabFolderStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.colabFolderStorage = colabFolderStorage;
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


    // ================ ColabFolder methods ==============================

    @Override
    public Path getColabFolderFilePath() {
        return colabFolderStorage.getColabFolderFilePath();
    }

    @Override
    public Optional<ReadOnlyColabFolder> readColabFolder() throws DataConversionException, IOException {
        return readColabFolder(colabFolderStorage.getColabFolderFilePath());
    }

    @Override
    public Optional<ReadOnlyColabFolder> readColabFolder(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return colabFolderStorage.readColabFolder(filePath);
    }

    @Override
    public void saveColabFolder(ReadOnlyColabFolder colabFolder) throws IOException {
        saveColabFolder(colabFolder, colabFolderStorage.getColabFolderFilePath());
    }

    @Override
    public void saveColabFolder(ReadOnlyColabFolder colabFolder, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        colabFolderStorage.saveColabFolder(colabFolder, filePath);
    }
}
