package seedu.budgetbaby.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.budgetbaby.commons.exceptions.DataConversionException;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;

/**
 * Represents a storage for {@link BudgetTracker}.
 */
public interface BudgetTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBudgetBabyFilePath();

    /**
     * Returns BudgetTracker data as a {@link ReadOnlyBudgetTracker}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBudgetTracker> readBudgetTracker() throws DataConversionException, IOException;

    /**
     * @see #getBudgetBabyFilePath()
     */
    Optional<ReadOnlyBudgetTracker> readBudgetTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBudgetTracker} to the storage.
     *
     * @param budgetTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker) throws IOException;

    /**
     * @see #saveBudgetTracker(ReadOnlyBudgetTracker)
     */
    void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker, Path filePath) throws IOException;

}
