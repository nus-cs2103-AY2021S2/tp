package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage, AppointmentScheduleStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    // =============== Patient Records ========================================================================= //
    Path getPatientRecordsFilePath();

    Optional<ReadOnlyAddressBook<Patient>> readPatientRecords() throws DataConversionException, IOException;

    void savePatientRecords(ReadOnlyAddressBook<Patient> patientRecords) throws IOException;

    // =============== Appointment Schedule ==================================================================== //
    @Override
    Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule() throws DataConversionException, IOException;

    @Override
    void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule) throws IOException;
}
