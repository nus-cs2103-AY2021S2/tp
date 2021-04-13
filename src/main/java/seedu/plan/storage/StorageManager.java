package seedu.plan.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.commons.exceptions.DataConversionException;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.ReadOnlyUserPrefs;
import seedu.plan.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModulePlannerStorage modulePlannerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModulePlannerStorage modulePlannerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.modulePlannerStorage = modulePlannerStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getPlansFilePath() {
        return modulePlannerStorage.getPlansFilePath();
    }

    @Override
    public Optional<ReadOnlyModulePlanner> readPlans() throws DataConversionException, IOException {
        return readPlans(modulePlannerStorage.getPlansFilePath());
    }

    @Override
    public Optional<ReadOnlyModulePlanner> readPlans(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return modulePlannerStorage.readPlans(filePath);
    }

    @Override
    public void savePlans(ReadOnlyModulePlanner addressBook) throws IOException {
        savePlans(addressBook, modulePlannerStorage.getPlansFilePath());
    }

    @Override
    public void savePlans(ReadOnlyModulePlanner addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        modulePlannerStorage.savePlans(addressBook, filePath);
    }
}
