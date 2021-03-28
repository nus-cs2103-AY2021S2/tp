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
import seedu.address.model.schedule.ReadOnlyScheduleTracker;

public class JsonScheduleTrackerStorage implements ScheduleTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonScheduleTrackerStorage.class);

    private Path filePath;

    public JsonScheduleTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getScheduleTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyScheduleTracker> readScheduleTracker() throws DataConversionException, IOException {
        return readScheduleTracker(filePath);
    }

    /**
     * Similar to {@link #readScheduleTracker(Path)} ()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyScheduleTracker> readScheduleTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableScheduleTracker> jsonSerializableScheduleTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableScheduleTracker.class);
        if (!jsonSerializableScheduleTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSerializableScheduleTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker) throws IOException {
        saveScheduleTracker(scheduleTracker, filePath);
    }

    /**
     * Similar to {@link #saveScheduleTracker(ReadOnlyScheduleTracker, Path)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker, Path filePath) throws IOException {
        requireNonNull(scheduleTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableScheduleTracker(scheduleTracker), filePath);
    }
}
