package seedu.booking.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.booking.commons.core.LogsCenter;
import seedu.booking.commons.exceptions.DataConversionException;
import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.commons.util.FileUtil;
import seedu.booking.commons.util.JsonUtil;
import seedu.booking.model.ReadOnlyBookingSystem;

/**
 * A class to access BookingSystem data stored as a json file on the hard disk.
 */
public class JsonBookingSystemStorage implements BookingSystemStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBookingSystemStorage.class);

    private Path filePath;

    public JsonBookingSystemStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookingSystemFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBookingSystem> readBookingSystem() throws DataConversionException {
        return readBookingSystem(filePath);
    }

    /**
     * Similar to {@link #readBookingSystem()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBookingSystem> readBookingSystem(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBookingSystem> jsonBookingSystem = JsonUtil.readJsonFile(
                filePath, JsonSerializableBookingSystem.class);
        if (!jsonBookingSystem.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBookingSystem.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBookingSystem(ReadOnlyBookingSystem bookingSystem) throws IOException {
        saveBookingSystem(bookingSystem, filePath);
    }

    /**
     * Similar to {@link #saveBookingSystem(ReadOnlyBookingSystem)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBookingSystem(ReadOnlyBookingSystem bookingSystem, Path filePath) throws IOException {
        requireNonNull(bookingSystem);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookingSystem(bookingSystem), filePath);
    }

}
