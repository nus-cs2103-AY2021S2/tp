package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.reminder.ReadOnlyReminderTracker;

public class JsonReminderTrackerStorage implements ReminderTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonReminderTrackerStorage.class);

    private final Path filePath;

    public JsonReminderTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getReminderTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyReminderTracker> readReminderTracker() throws DataConversionException, IOException {
        return readReminderTracker(filePath);
    }

    /**
     * Similar to {@link #readReminderTracker(Path)} ()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyReminderTracker> readReminderTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableReminderTracker> jsonSerializableReminderTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableReminderTracker.class);
        if (!jsonSerializableReminderTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSerializableReminderTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveReminderTracker(ReadOnlyReminderTracker reminderTracker) throws IOException {
        saveReminderTracker(reminderTracker, filePath);
    }

    /**
     * Similar to {@link #saveReminderTracker(ReadOnlyReminderTracker, Path)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveReminderTracker(ReadOnlyReminderTracker reminderTracker, Path filePath) throws IOException {
        requireAllNonNull(reminderTracker, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableReminderTracker(reminderTracker), filePath);
    }
}
