package seedu.budgetbaby.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.commons.exceptions.DataConversionException;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.ReadOnlyUserPrefs;
import seedu.budgetbaby.model.UserPrefs;

/**
 * Manages storage of BudgetBaby data in local storage.
 */
public class BudgetBabyStorageManager implements BudgetBabyStorage {

    private static final Logger logger = LogsCenter.getLogger(BudgetBabyStorageManager.class);
    private BudgetTrackerStorage budgetTrackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BudgetTrackerStorage} and {@code UserPrefStorage}.
     */
    public BudgetBabyStorageManager(BudgetTrackerStorage budgetTrackerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.budgetTrackerStorage = budgetTrackerStorage;
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
    public Path getBudgetBabyFilePath() {
        return budgetTrackerStorage.getBudgetBabyFilePath();
    }

    @Override
    public Optional<ReadOnlyBudgetTracker> readBudgetTracker() throws DataConversionException, IOException {
        return readBudgetTracker(budgetTrackerStorage.getBudgetBabyFilePath());
    }

    @Override
    public Optional<ReadOnlyBudgetTracker> readBudgetTracker(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return budgetTrackerStorage.readBudgetTracker(filePath);
    }

    @Override
    public void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker) throws IOException {
        saveBudgetTracker(budgetTracker, budgetTrackerStorage.getBudgetBabyFilePath());
    }

    @Override
    public void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        budgetTrackerStorage.saveBudgetTracker(budgetTracker, filePath);
    }

}
