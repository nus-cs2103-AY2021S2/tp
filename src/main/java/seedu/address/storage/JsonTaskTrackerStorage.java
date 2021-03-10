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
import seedu.address.model.ReadOnlyTaskTracker;

/**
 * A class to access TaskTracker data stored as a json file on the hard disk.
 */
public class JsonTaskTrackerStorage implements TaskTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskTrackerStorage.class);

    private Path filePath;

    public JsonTaskTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskTracker> readTaskTracker() throws DataConversionException {
        return readTaskTracker(filePath);

    }

    /**
     * Similar to {@link #readTaskTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskTracker> readTaskTracker(Path filePath) throws DataConversionException {

        requireNonNull(filePath);

        Optional<JsonSerializableTaskTracker> jsonTaskTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskTracker.class);
        if (!jsonTaskTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskTracker(ReadOnlyTaskTracker taskTracker) throws IOException {
        saveTaskTracker(taskTracker, filePath);
    }

    /**
     * Similar to {@link #saveTaskTracker(ReadOnlyTaskTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskTracker(ReadOnlyTaskTracker taskTracker, Path filePath) throws IOException {
        requireNonNull(taskTracker);

        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskTracker(taskTracker), filePath);
    }

}
