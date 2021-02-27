package seedu.us.among.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.exceptions.DataConversionException;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.ReadOnlyUserPrefs;
import seedu.us.among.model.UserPrefs;

/**
 * Manages storage of EndpointList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EndpointListStorage endpointListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EndpointListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EndpointListStorage endpointListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.endpointListStorage = endpointListStorage;
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


    // ================ EndpointList methods ==============================

    @Override
    public Path getEndpointListFilePath() {
        return endpointListStorage.getEndpointListFilePath();
    }

    @Override
    public Optional<ReadOnlyEndpointList> readEndpointList() throws DataConversionException, IOException {
        return readEndpointList(endpointListStorage.getEndpointListFilePath());
    }

    @Override
    public Optional<ReadOnlyEndpointList> readEndpointList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return endpointListStorage.readEndpointList(filePath);
    }

    @Override
    public void saveEndpointList(ReadOnlyEndpointList endpointList) throws IOException {
        saveEndpointList(endpointList, endpointListStorage.getEndpointListFilePath());
    }

    @Override
    public void saveEndpointList(ReadOnlyEndpointList endpointList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        endpointListStorage.saveEndpointList(endpointList, filePath);
    }

}
