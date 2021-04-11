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
import seedu.address.model.ReadOnlyAppointmentSchedule;

/**
 * A class to access AppointmentSchedule data stored as a json file on the hard disk.
 */
public class JsonAppointmentScheduleStorage implements AppointmentScheduleStorage {

    private static final Logger LOGGER = LogsCenter.getLogger(JsonAppointmentScheduleStorage.class);

    private Path filePath;

    public JsonAppointmentScheduleStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAppointmentScheduleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule() throws DataConversionException {
        return readAppointmentSchedule(filePath);
    }

    /**
     * Similar to {@link #readAppointmentSchedule()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAppointmentSchedule> jsonAppointmentSchedule = JsonUtil.readJsonFile(
                filePath, JsonSerializableAppointmentSchedule.class);
        if (!jsonAppointmentSchedule.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointmentSchedule.get().toModelType());
        } catch (IllegalValueException ive) {
            LOGGER.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule) throws IOException {
        saveAppointmentSchedule(appointmentSchedule, filePath);
    }

    /**
     * Similar to {@link #saveAppointmentSchedule(ReadOnlyAppointmentSchedule)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule, Path filePath) throws
            IOException {

        requireNonNull(appointmentSchedule);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentSchedule(appointmentSchedule), filePath);
    }

}
