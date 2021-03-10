package seedu.budgetbaby.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.budgetbaby.commons.exceptions.DataConversionException;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.ReadOnlyUserPrefs;
import seedu.budgetbaby.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface BudgetBabyStorage extends BudgetTrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getBudgetBabyFilePath();

    @Override
    Optional<ReadOnlyBudgetTracker> readBudgetTracker() throws DataConversionException, IOException;

    @Override
    void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker) throws IOException;

}
