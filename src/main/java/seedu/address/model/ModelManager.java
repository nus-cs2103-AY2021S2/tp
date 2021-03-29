package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * Represents the in-memory model of the app data.
 */
public class ModelManager implements Model {
    private static final Logger LOGGER = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final AddressBook<Patient> patientRecords;
    private final AddressBook<Doctor> doctorRecords;
    private final AppointmentSchedule appointmentSchedule;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Doctor> filteredDoctors;
    private final FilteredList<Appointment> filteredAppointments;

    /**
     * Initializes a ModelManager with the given patientRecords, doctorRecords, appointmentSchedule and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook<Patient> patientRecords, ReadOnlyAddressBook<Doctor> doctorRecords,
                        ReadOnlyAppointmentSchedule appointmentSchedule, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(appointmentSchedule, patientRecords, userPrefs);

        LOGGER.fine("Initializing with patientRecords: " + patientRecords
                + " and doctorRecords: " + doctorRecords
                + " and appointment schedule: " + appointmentSchedule
                + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.patientRecords = new AddressBook<>(patientRecords);
        this.doctorRecords = new AddressBook<>(doctorRecords);
        this.appointmentSchedule = new AppointmentSchedule(appointmentSchedule);


        filteredPatients = new FilteredList<>(this.patientRecords.getPersonList());
        filteredDoctors = new FilteredList<>(this.doctorRecords.getPersonList());
        filteredAppointments = new FilteredList<>(this.appointmentSchedule.getAppointmentList());
    }

    public ModelManager() {
        this(new AddressBook<>(), new AddressBook<>(), new AppointmentSchedule(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    //=========== PatientRecords ================================================================================

    @Override
    public Path getPatientRecordsFilePath() {
        return userPrefs.getPatientRecordsFilePath();
    }

    @Override
    public void setPatientRecordsFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPatientRecordsFilePath(addressBookFilePath);
    }

    @Override
    public void setPatientRecords(ReadOnlyAddressBook<Patient> patientRecords) {
        this.patientRecords.resetData(patientRecords);
    }

    @Override
    public ReadOnlyAddressBook<Patient> getPatientRecords() {
        return patientRecords;
    }

    @Override
    public boolean hasConflictingUuid(UUID uuid) {
        requireNonNull(uuid);
        return patientRecords.hasConflictingUuid(uuid)
                || doctorRecords.hasConflictingUuid(uuid);
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patientRecords.hasPerson(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        patientRecords.removePerson(target);
    }

    @Override
    public void addPatient(Patient patient) {
        patientRecords.addPerson(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        patientRecords.setPerson(target, editedPatient);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<? super Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== DoctorRecords ================================================================================

    @Override
    public Path getDoctorRecordsFilePath() {
        return userPrefs.getDoctorRecordsFilePath();
    }

    @Override
    public void setDoctorRecordsFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPatientRecordsFilePath(addressBookFilePath);
    }

    @Override
    public void setDoctorRecords(ReadOnlyAddressBook<Doctor> doctorRecords) {
        this.doctorRecords.resetData(doctorRecords);
    }

    @Override
    public ReadOnlyAddressBook<Doctor> getDoctorRecords() {
        return doctorRecords;
    }

    @Override
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctorRecords.hasPerson(doctor);
    }

    @Override
    public void deleteDoctor(Doctor target) {
        doctorRecords.removePerson(target);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctorRecords.addPerson(doctor);
        updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
    }

    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);
        doctorRecords.setPerson(target, editedDoctor);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return filteredDoctors;
    }

    @Override
    public void updateFilteredDoctorList(Predicate<? super Doctor> predicate) {
        requireNonNull(predicate);
        filteredDoctors.setPredicate(predicate);
    }

    //=========== AppointmentSchedule ========================================================================
    @Override
    public Path getAppointmentScheduleFilePath() {
        return userPrefs.getAppointmentScheduleFilePath();
    }

    @Override
    public void setAppointmentScheduleFilePath(Path appointmentScheduleFilePath) {
        requireNonNull(appointmentScheduleFilePath);
        userPrefs.setAppointmentScheduleFilePath(appointmentScheduleFilePath);
    }

    @Override
    public void setAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule) {
        this.appointmentSchedule.resetData(appointmentSchedule);
    }

    @Override
    public ReadOnlyAppointmentSchedule getAppointmentSchedule() {
        return appointmentSchedule;
    }

    @Override
    public boolean hasPatientInAppointmentSchedule(Patient patient) {
        requireNonNull(patient);
        return appointmentSchedule.hasPatientInSchedule(patient);
    }

    @Override
    public boolean hasDoctorInAppointmentSchedule(Doctor doctor) {
        requireNonNull(doctor);
        return appointmentSchedule.hasDoctorInSchedule(doctor);
    }

    @Override
    public boolean hasConflictingAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentSchedule.hasConflict(appointment);
    }

    @Override
    public boolean hasConflictingAppointmentExcludingTarget(Appointment target, Appointment appointment) {
        requireNonNull(appointment);
        return appointmentSchedule.hasConflictExcludingTarget(target, appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        appointmentSchedule.removeAppointment(target);
    }

    @Override
    public void deletePatientAppointments(UUID patientUuid) {
        requireNonNull(patientUuid);
        appointmentSchedule.deletePatientAppointments(patientUuid);
    }

    @Override
    public void deleteDoctorAppointments(UUID doctorUuid) {
        requireNonNull(doctorUuid);
        appointmentSchedule.deleteDoctorAppointments(doctorUuid);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentSchedule.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        appointmentSchedule.setAppointment(target, editedAppointment);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAppointmentSchedule}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return userPrefs.equals(other.userPrefs)
                && patientRecords.equals(other.patientRecords)
                && doctorRecords.equals(other.doctorRecords)
                && appointmentSchedule.equals(other.appointmentSchedule)
                && filteredPatients.equals(other.filteredPatients)
                && filteredDoctors.equals(other.filteredDoctors)
                && filteredAppointments.equals(other.filteredAppointments);
    }
}
