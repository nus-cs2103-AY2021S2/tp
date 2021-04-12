package seedu.budgetbaby.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.commons.exceptions.DataConversionException;
import seedu.budgetbaby.commons.exceptions.IllegalValueException;
import seedu.budgetbaby.commons.util.FileUtil;
import seedu.budgetbaby.commons.util.JsonUtil;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;

/**
 * A class to access BudgetTracker data stored as a json file on the hard disk.
 */
public class JsonBudgetTrackerStorage implements BudgetTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBudgetTrackerStorage.class);

    private Path filePath;

    public JsonBudgetTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBudgetBabyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBudgetTracker> readBudgetTracker() throws DataConversionException {
        return readBudgetTracker(filePath);
    }

    /**
     * Similar to {@link #readBudgetTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBudgetTracker> readBudgetTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBudgetTracker> jsonBudgetTracker;

        try {
            jsonBudgetTracker = JsonUtil.readJsonFile(filePath, JsonSerializableBudgetTracker.class);
        } catch (IllegalArgumentException iae) {
            logger.info("Illegal values found in " + filePath + ": " + iae.getMessage());
            throw new DataConversionException(iae);
        }

        if (!jsonBudgetTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBudgetTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker) throws IOException {
        saveBudgetTracker(budgetTracker, filePath);
    }

    /**
     * Similar to {@link #saveBudgetTracker(ReadOnlyBudgetTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBudgetTracker(ReadOnlyBudgetTracker budgetTracker, Path filePath) throws IOException {
        requireNonNull(budgetTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBudgetTracker(budgetTracker), filePath);
    }

}
