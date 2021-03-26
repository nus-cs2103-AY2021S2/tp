package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger LOGGER = LogsCenter.getLogger(StorageManager.class);
    private AppointmentScheduleStorage appointmentScheduleStorage;
    private AddressBookStorage<Patient> patientRecordsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} patient records
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AppointmentScheduleStorage appointmentScheduleStorage,
                          AddressBookStorage<Patient> patientRecordsStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.appointmentScheduleStorage = appointmentScheduleStorage;
        this.patientRecordsStorage = patientRecordsStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Patient Records ============================================================================ //

    @Override
    public Path getPatientRecordsFilePath() {
        return patientRecordsStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook<Patient>> readPatientRecords() throws DataConversionException, IOException {
        return readPatientRecords(patientRecordsStorage.getAddressBookFilePath());
    }

    /**
     * Returns a {@code ReadOnlyAddressBook<Patient>} representing patient records if {@code filePath} exists.
     */
    public Optional<ReadOnlyAddressBook<Patient>> readPatientRecords(Path filePath) throws
            DataConversionException, IOException {
        LOGGER.fine("Attempting to read data from file: " + filePath);
        return patientRecordsStorage.readAddressBook(filePath);
    }

    @Override
    public void savePatientRecords(ReadOnlyAddressBook<Patient> addressBook) throws IOException {
        savePatientRecords(addressBook, patientRecordsStorage.getAddressBookFilePath());
    }

    /**
     * Saves a {@code ReadOnlyAddressBook<Patient>} representing patient records based on {@code filePath}.
     */
    public void savePatientRecords(ReadOnlyAddressBook<Patient> addressBook, Path filePath) throws IOException {
        LOGGER.fine("Attempting to write to data file: " + filePath);
        patientRecordsStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Appointment Schedule ======================================================================= //

    @Override
    public Path getAppointmentScheduleFilePath() {
        return appointmentScheduleStorage.getAppointmentScheduleFilePath();
    }

    @Override
    public Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule() throws DataConversionException, IOException {
        return readAppointmentSchedule(appointmentScheduleStorage.getAppointmentScheduleFilePath());
    }

    @Override
    public Optional<ReadOnlyAppointmentSchedule> readAppointmentSchedule(Path filePath)
            throws DataConversionException, IOException {

        LOGGER.fine("Attempting to read data from file: " + filePath);
        return appointmentScheduleStorage.readAppointmentSchedule(filePath);
    }

    @Override
    public void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule) throws IOException {
        saveAppointmentSchedule(appointmentSchedule, appointmentScheduleStorage.getAppointmentScheduleFilePath());
    }

    @Override
    public void saveAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule, Path filePath)
            throws IOException {
        LOGGER.fine("Attempting to write to data file: " + filePath);
        appointmentScheduleStorage.saveAppointmentSchedule(appointmentSchedule, filePath);
    }
}
