package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAppointmentSchedule;

/**
 * Represents a storage for {@link seedu.address.model.AppointmentSchedule}.
 */
public interface AppointmentScheduleStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getAppointmentScheduleFilePath();

    /**
     * Returns AppointmentSchedule data as a {@link ReadOnlyAppointmentSchedule}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule() throws DataConversionException, IOException;

    /**
     * @see #getAppointmentScheduleFilePath()
     */
    Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyAppointmentSchedule} to the storage.
     * @param appointmentSchedule cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule) throws IOException;

    /**
     * @see #saveAppointmentSchedule(ReadOnlyAppointmentSchedule)
     */
    void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule, Path filePath) throws IOException;

}
