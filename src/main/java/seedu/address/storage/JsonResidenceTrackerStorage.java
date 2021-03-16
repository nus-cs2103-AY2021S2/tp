package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyResidenceTracker;

/**
 * A class to access ResidenceTracker data stored as a json file on the hard disk.
 */
public class JsonResidenceTrackerStorage implements ResidenceTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonResidenceTrackerStorage.class);

    private Path filePath;

    public JsonResidenceTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getResidenceTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyResidenceTracker> readResidenceTracker() throws DataConversionException {
        return readResidenceTracker(filePath);
    }

    /**
     * Similar to {@link #readResidenceTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyResidenceTracker> readResidenceTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableResidenceTracker> jsonResidenceTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableResidenceTracker.class);
        if (!jsonResidenceTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonResidenceTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker) throws IOException {
        saveResidenceTracker(residenceTracker, filePath);
    }

    /**
     * Similar to {@link #saveResidenceTracker(ReadOnlyResidenceTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker, Path filePath) throws IOException {
        requireNonNull(residenceTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResidenceTracker(residenceTracker), filePath);
    }

}
